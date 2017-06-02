package com.softuni.webstore.dao;

import com.softuni.webstore.entity.OrderType;

public interface OrderTypeDao {
	public OrderType getOrderTypeById (long id);
	public OrderType getOrderTypeByName(String name);
}
