package com.example.order_service.dtos.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotBlank
    private String productName;

    @NotNull @Min(1)
    private Integer quantity;

    @NotNull @DecimalMin("0.0")
    private Double unitPrice;
}
