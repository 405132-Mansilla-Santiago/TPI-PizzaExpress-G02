package com.example.order_service.services.impl;

import java.time.OffsetDateTime;

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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public OrderModel createOrder(OrderCreateRequest request) {

        OrderModel model = mapper.map(request, OrderModel.class);

        model.setStatus(OrderStatus.PENDING);
        model.setCreatedAt(OffsetDateTime.now());
        model.setUpdatedAt(OffsetDateTime.now());

        double total = model.getItems().stream()
                .mapToDouble(i -> i.getUnitPrice() * i.getQuantity())
                .sum();
        model.setTotal(total);

        Order entity = mapper.map(model, Order.class);

        entity.getItems().forEach(item -> item.setOrder(entity));

        entity.setId(null);
        entity.getItems().forEach(i -> i.setId(null));

        Order saved = orderRepository.save(entity);

        return mapper.map(saved, OrderModel.class);
    }

    @Override
    public Page<OrderModel> listOrders(
            OrderStatus status,
            OrderChannel channel,
            OffsetDateTime from,
            OffsetDateTime to,
            Pageable pageable) {
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
        entity.setUpdatedAt(OffsetDateTime.now());

        Order updated = orderRepository.save(entity);
        return mapper.map(updated, OrderModel.class);
    }

    @Override
    public void cancelOrder(Long id) {
        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        entity.setStatus(OrderStatus.CANCELLED);
        entity.setUpdatedAt(OffsetDateTime.now());

        orderRepository.save(entity);
    }
}
