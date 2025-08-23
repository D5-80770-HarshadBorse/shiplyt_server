package com.shiplyt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiplyt.exception.DuplicateOrderException;
import com.shiplyt.model.dto.OrderRequest;
import com.shiplyt.model.entity.Order;
import com.shiplyt.model.entity.User;
import com.shiplyt.repository.OrderRepository;
import com.shiplyt.service.OrderService;
import com.shiplyt.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final SecurityUtil securityUtil;
	private final OrderAssignmentServiceImpl orderAssignmentServiceImpl;

	@Override
	public Order createOrder(OrderRequest orderRequest) {

		User loggedInUser = securityUtil.getLoggedInUser();

		if (isDuplicateOrder(loggedInUser, orderRequest)) {
			throw new DuplicateOrderException("A similar order already exists for this user.", HttpStatus.BAD_REQUEST);
		}

		Order order = Order.builder().user(loggedInUser).pickupLat(orderRequest.getPickupLat())
				.pickupLng(orderRequest.getPickupLng()).dropLat(orderRequest.getDropLat())
				.dropLng(orderRequest.getDropLng()).consigneeName(orderRequest.getConsigneeName())
				.consigneePhone(orderRequest.getConsigneePhone()).consigneeAddress(orderRequest.getConsigneeAddress())
				.specialInstructions(orderRequest.getSpecialInstructions()).status(Order.Status.CREATED).build();

		Order savedOrder = orderRepository.save(order);
		orderAssignmentServiceImpl.assignDriversToOrders();
		return savedOrder;
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order updateOrderStatus(Long id, Order.Status status) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public boolean isDuplicateOrder(User user, OrderRequest request) {
		return orderRepository.existsByUserAndPickupLatAndPickupLngAndDropLatAndDropLng(user, request.getPickupLat(),
				request.getPickupLng(), request.getDropLat(), request.getDropLng());
	}
}
