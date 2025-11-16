package com.example.order_service.controllers;

import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.order_service.domain.enums.OrderChannel;
import com.example.order_service.domain.enums.OrderStatus;
import com.example.order_service.domain.models.OrderModel;
import com.example.order_service.dtos.request.OrderCreateRequest;
import com.example.order_service.dtos.request.OrderStatusRequest;
import com.example.order_service.dtos.response.ApiResponse;
import com.example.order_service.dtos.response.OrderResponse;
import com.example.order_service.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @Valid @RequestBody OrderCreateRequest request
    ) {
        OrderModel model = orderService.createOrder(request);
        OrderResponse response = mapper.map(model, OrderResponse.class);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order created successfully",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderResponse>>> list(
            @RequestParam(name = "status", required = false) OrderStatus status,
            @RequestParam(name = "channel", required = false) OrderChannel channel,
            @RequestParam(name = "from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
            @RequestParam(name = "to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,
            Pageable pageable
    ) {
        Page<OrderResponse> page = orderService
                .listOrders(status, channel, from, to, pageable)
                .map(model -> mapper.map(model, OrderResponse.class));

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Orders retrieved successfully",
                        page
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable("id") Long id) {
        OrderModel model = orderService.getById(id);
        OrderResponse response = mapper.map(model, OrderResponse.class);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order retrieved successfully",
                        response
                )
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(
            @PathVariable("id") Long id,
            @Valid @RequestBody OrderStatusRequest request
    ) {
        OrderModel model = orderService.updateStatus(id, request.getNewStatus());
        OrderResponse response = mapper.map(model, OrderResponse.class);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order status updated successfully",
                        response
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> cancel(@PathVariable("id") Long id) {
        OrderModel order = orderService.cancelOrder(id);

        OrderResponse response = mapper.map(order, OrderResponse.class);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order cancelled successfully",
                        response
                )
        );
    }
}
