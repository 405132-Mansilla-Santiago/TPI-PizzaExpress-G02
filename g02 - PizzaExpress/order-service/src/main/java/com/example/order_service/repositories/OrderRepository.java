package com.example.order_service.repositories;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.order_service.domain.entities.Order;
import com.example.order_service.domain.enums.OrderChannel;
import com.example.order_service.domain.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
    SELECT o FROM Order o
    WHERE (COALESCE(:status, o.status) = o.status)
    AND (COALESCE(:channel, o.channel) = o.channel)
    AND (COALESCE(:fromDate, o.createdAt) <= o.createdAt)
    AND (COALESCE(:toDate, o.createdAt) >= o.createdAt)
    """)
Page<Order> filterOrders(
        @Param("status") OrderStatus status,
        @Param("channel") OrderChannel channel,
        @Param("fromDate") OffsetDateTime from,
        @Param("toDate") OffsetDateTime to,
        Pageable pageable
);

}
