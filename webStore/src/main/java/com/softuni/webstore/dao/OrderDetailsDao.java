package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.OrderDetails;

public interface OrderDetailsDao {
	public OrderDetails getOrderDetailsByProductId(long id);
	public List<OrderDetails> getOrderDetailsByOrderId(long id);
}
