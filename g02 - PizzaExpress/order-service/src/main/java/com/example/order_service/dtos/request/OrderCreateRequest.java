package com.example.order_service.dtos.request;

import java.util.List;

import com.example.order_service.domain.enums.OrderChannel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    @NotNull
    private OrderChannel channel;

    private String customerName;

    @NotEmpty
    @Valid
    private List<OrderItemRequest> items;
}
