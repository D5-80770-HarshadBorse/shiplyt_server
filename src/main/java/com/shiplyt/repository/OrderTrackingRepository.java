package com.shiplyt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiplyt.model.entity.OrderTracking;

import java.util.Optional;

public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {
    Optional<OrderTracking> findByOrderId(Long orderId);
}
