package com.example.order_service.dtos.request;

import com.example.order_service.domain.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {

    @NotNull
    private OrderStatus newStatus;
}
