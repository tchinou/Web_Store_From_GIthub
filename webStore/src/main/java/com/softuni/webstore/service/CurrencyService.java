package com.softuni.webstore.service;

import com.softuni.webstore.entity.Currency;

public interface CurrencyService {
	public Currency getCurrencyById(long id);
	public Currency getCurrencyByName(String name);
}
