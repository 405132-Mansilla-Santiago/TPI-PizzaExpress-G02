package com.example.order_service.domain.models;

import java.time.OffsetDateTime;
import java.util.List;

import com.example.order_service.domain.enums.OrderChannel;
import com.example.order_service.domain.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    private Long id;
    private OrderChannel channel;
    private String customerId;
    private List<OrderItemModel> items;
    private Double total;
    private OrderStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
