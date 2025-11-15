package com.example.order_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

    private Long id;
    private String productId;
    private Integer quantity;
    private Double unitPrice;
}
