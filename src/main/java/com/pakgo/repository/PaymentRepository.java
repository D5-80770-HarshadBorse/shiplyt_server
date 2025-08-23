package com.pakgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakgo.model.entity.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> { }
