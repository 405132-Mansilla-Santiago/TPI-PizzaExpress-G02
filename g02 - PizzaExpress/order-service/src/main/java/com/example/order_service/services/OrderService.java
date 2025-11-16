package com.example.order_service.services;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.order_service.domain.enums.OrderChannel;
import com.example.order_service.domain.enums.OrderStatus;
import com.example.order_service.domain.models.OrderModel;
import com.example.order_service.dtos.request.OrderCreateRequest;

public interface OrderService {

    OrderModel createOrder(OrderCreateRequest request);

    Page<OrderModel> listOrders(
            OrderStatus status,
            OrderChannel channel,
            OffsetDateTime from,
            OffsetDateTime to,
            Pageable pageable
    );

    OrderModel getById(Long id);

    OrderModel updateStatus(Long id, OrderStatus status);

    OrderModel cancelOrder(Long id);
}
