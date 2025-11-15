package com.example.order_service.repositories;

import java.time.LocalDateTime;

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
        WHERE (:status IS NULL OR o.status = :status)
        AND (:channel IS NULL OR o.channel = :channel)
        AND (:fromDate IS NULL OR o.createdAt >= :fromDate)
        AND (:toDate IS NULL OR o.createdAt <= :toDate)
    """)
    Page<Order> filterOrders(
            @Param("status") OrderStatus status,
            @Param("channel") OrderChannel channel,
            @Param("fromDate") LocalDateTime from,
            @Param("toDate") LocalDateTime to,
            Pageable pageable
    );
}
