package com.softuni.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.dao.OrderTypeDao;
import com.softuni.webstore.entity.OrderType;

@Service
public class OrderTypeServiceImpl implements OrderTypeService{

	@Autowired
	OrderTypeDao orderTypeDao;
	
	@Override
	public OrderType getOrderTypeById(long id) {
		return orderTypeDao.getOrderTypeById(id);
	}

	@Override
	public OrderType getOrderTypeByName(String name) {
		return orderTypeDao.getOrderTypeByName(name);
	}
}
