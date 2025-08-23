package com.shiplyt.service;

import java.util.List;
import java.util.Optional;

import com.shiplyt.model.dto.OrderRequest;
import com.shiplyt.model.entity.Order;
import com.shiplyt.model.entity.User;

public interface OrderService {
	Order createOrder(OrderRequest orderRequest);

	Optional<Order> getOrderById(Long id);

	List<Order> getAllOrders();

	Order updateOrderStatus(Long id, Order.Status status);

	void deleteOrder(Long id);

	boolean isDuplicateOrder(User user, OrderRequest request);
}
