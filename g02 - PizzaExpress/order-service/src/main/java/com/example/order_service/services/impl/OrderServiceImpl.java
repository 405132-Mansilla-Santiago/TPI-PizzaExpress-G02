package com.example.order_service.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.order_service.domain.entities.Order;
import com.example.order_service.domain.enums.OrderChannel;
import com.example.order_service.domain.enums.OrderStatus;
import com.example.order_service.domain.models.OrderModel;
import com.example.order_service.dtos.request.OrderCreateRequest;
import com.example.order_service.repositories.OrderRepository;
import com.example.order_service.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public OrderModel createOrder(OrderCreateRequest request) {

        // Map DTO → Model
        OrderModel model = mapper.map(request, OrderModel.class);

        model.setStatus(OrderStatus.PENDING);
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());

        // Calculate total
        double total = model.getItems().stream()
                .mapToDouble(i -> i.getUnitPrice() * i.getQuantity())
                .sum();
        model.setTotal(total);

        // Map Model → Entity
        Order entity = mapper.map(model, Order.class);

        // Fix parent relationship
        entity.getItems().forEach(item -> item.setOrder(entity));

        // Save
        Order saved = orderRepository.save(entity);

        return mapper.map(saved, OrderModel.class);
    }

    @Override
    public Page<OrderModel> listOrders(
            OrderStatus status,
            OrderChannel channel,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable
    ) {
        return orderRepository
                .filterOrders(status, channel, from, to, pageable)
                .map(entity -> mapper.map(entity, OrderModel.class));
    }

    @Override
    public OrderModel getById(Long id) {
        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapper.map(entity, OrderModel.class);
    }

    @Override
    public OrderModel updateStatus(Long id, OrderStatus newStatus) {
        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        entity.setStatus(newStatus);
        entity.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepository.save(entity);
        return mapper.map(updated, OrderModel.class);
    }

    @Override
    public void cancelOrder(Long id) {
        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        entity.setStatus(OrderStatus.CANCELLED);
        entity.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(entity);
    }
}
