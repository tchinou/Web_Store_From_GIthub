package com.softuni.webstore.dao;

import com.softuni.webstore.entity.Currency;

public interface CurrencyDao {
	public Currency getCurrencyById(long id);
	public Currency getCurrencyByName(String name);
}
