package com.example.order_service.dtos.response;

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
public class OrderResponse {

    private Long id;
    private OrderChannel channel;
    private String customerId;
    private List<OrderItemResponse> items;
    private Double total;
    private OrderStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
