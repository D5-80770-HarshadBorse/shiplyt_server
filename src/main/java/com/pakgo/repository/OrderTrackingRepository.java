package com.pakgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakgo.model.entity.OrderTracking;

import java.util.Optional;

public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {
    Optional<OrderTracking> findByOrderId(Long orderId);
}
