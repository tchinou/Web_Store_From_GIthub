package com.softuni.webstore.service;

import java.util.List;

import com.softuni.webstore.entity.Order;
import com.softuni.webstore.entity.OrderDetails;
import com.softuni.webstore.entity.Product;

public interface OrderDetailsService {
	public OrderDetails addProductToCart(Product product, Order order);
	public Order removeProductFromCart(Order order, int rowIndex);
	public OrderDetails getOrderDetailsByProductId(long id);
	public List<OrderDetails> getOrderDetailsByOrderId(long id);
}
