package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.ProductType;

public interface ProductTypeDao {
	public ProductType getProductTypeById(long id);
	public ProductType getProductTypeByName(String name);
	public List<ProductType> getProductTypes();
}
