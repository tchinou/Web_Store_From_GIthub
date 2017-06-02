package com.softuni.webstore.service;

import java.util.List;

import com.softuni.webstore.entity.ProductType;

public interface ProductTypeService {
	public ProductType getProductTypeById(long id);
	public ProductType getProductTypeByName(String name);
	public List<ProductType> getProductTypes();
}
