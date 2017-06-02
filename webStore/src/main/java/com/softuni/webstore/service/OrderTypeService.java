package com.softuni.webstore.service;

import com.softuni.webstore.entity.OrderType;

public interface OrderTypeService {
	public OrderType getOrderTypeById (long id);
	public OrderType getOrderTypeByName(String name);
}
