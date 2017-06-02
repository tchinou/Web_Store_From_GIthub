package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.Order;

public interface OrderDao {
	public boolean addOrder(Order order);
	public boolean editOrder(Order order);
	public Order getOrderById(long id);
	public Order getOrderCustomerId(long id);
	public List<Order> getOrdersByProductName(String name);
	public List<Order> searchByCriteria(String criteria, Object value, String operation);
	public List<Order> getOrders();
}
