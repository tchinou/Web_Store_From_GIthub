package com.softuni.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.dao.OrderDetailsDao;
import com.softuni.webstore.entity.Order;
import com.softuni.webstore.entity.OrderDetails;
import com.softuni.webstore.entity.Product;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsDao orderDetailsDao;
	
	@Override
	public OrderDetails addProductToCart(Product product, Order order) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProduct(product);
		orderDetails.setPrice(product.getSinglePrice());
		orderDetails.setCurrency(product.getCurrency());
		orderDetails.setQuantity(1);
		orderDetails.setOrder(order);;
		return orderDetails;
	}

	@Override
	public Order removeProductFromCart(Order order, int rowIndex) {
		order.getOrderDetails().remove(rowIndex);
		return order;
	}

	@Override
	public OrderDetails getOrderDetailsByProductId(long id) {
		return orderDetailsDao.getOrderDetailsByProductId(id);
	}

	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(long id) {
		return orderDetailsDao.getOrderDetailsByOrderId(id);
	}
}
