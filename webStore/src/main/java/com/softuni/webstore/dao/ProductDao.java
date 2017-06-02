package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.Product;

public interface ProductDao {
	public boolean addProduct(Product product);
	public boolean editProduct(Product product);
	public boolean deleteProduct(long id);
	public boolean activateProduct(Product product);
	public boolean deactivateProduct(Product product);
	public List<Product> searchByCriteria(String criteria, Object value, String operation, boolean showActive);
	public Product getProductById(Long id);
	public List<Product> getAllProducts();
}
