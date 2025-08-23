package com.pakgo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pakgo.model.entity.Driver;
import com.pakgo.model.entity.Order;
import com.pakgo.repository.DriverRepository;
import com.pakgo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderAssignmentServiceImpl {

	private final OrderRepository orderRepository;
	private final DriverRepository driverProfileRepository;

	@Async
	public void assignDriversToOrders() {
		List<Order> orders = orderRepository.findByStatusAndDriverIsNull(Order.Status.CREATED);
		if (orders.isEmpty())
			return;

		List<Driver> drivers = driverProfileRepository.findByStatus(Driver.Status.ONLINE);
		if (drivers.isEmpty())
			return;

		Iterator<Driver> driverIterator = drivers.iterator();

		for (Order order : orders) {
			if (!driverIterator.hasNext())
				break;

			Driver driver = driverIterator.next();

			order.setDriver(driver);
			order.setStatus(Order.Status.ASSIGNED);
			orderRepository.save(order);

			driver.setStatus(Driver.Status.ASSIGNED);
			driverProfileRepository.save(driver);
		}
	}

}
