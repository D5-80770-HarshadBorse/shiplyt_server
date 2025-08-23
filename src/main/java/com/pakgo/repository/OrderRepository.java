package com.pakgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakgo.model.entity.Driver;
import com.pakgo.model.entity.Order;
import com.pakgo.model.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser(User user);

	List<Order> findByDriver(Driver driver);

	boolean existsByUserAndPickupLatAndPickupLngAndDropLatAndDropLng(User user, Double pickupLat, Double pickupLng,
			Double dropLat, Double dropLng);

	List<Order> findByStatusAndDriverIsNull(Order.Status status);
}
