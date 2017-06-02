package com.softuni.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.dao.CurrencyDao;
import com.softuni.webstore.entity.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyService{

	@Autowired
	CurrencyDao currencyDao;
	
	@Override
	public Currency getCurrencyById(long id) {
		return currencyDao.getCurrencyById(id);
	}

	@Override
	public Currency getCurrencyByName(String name) {
		return currencyDao.getCurrencyByName(name);
	}

}
