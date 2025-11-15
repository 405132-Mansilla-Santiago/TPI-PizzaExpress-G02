package com.example.order_service.domain.models;

import java.time.LocalDateTime;
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
    private String customerName;
    private List<OrderItemModel> items;
    private Double total;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
