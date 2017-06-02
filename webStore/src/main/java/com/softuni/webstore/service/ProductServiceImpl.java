package com.softuni.webstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.constants.Constants;
import com.softuni.webstore.dao.ProductDao;
import com.softuni.webstore.entity.Product;
import com.softuni.webstore.log4j.LoggerManager;

@Service
public class ProductServiceImpl implements ProductService{

	private Logger systemlog = LoggerManager.getSystemLogger();
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public boolean editProduct(Product product) {
		return productDao.editProduct(product);
	}

	@Override
	public boolean deleteProduct(long id) {
		return productDao.deleteProduct(id);
	}

	@Override
	public List<Product> searchByCriteria(String criteria, Object value, String operation) {
		if ((Constants.OPERATION_CRITERIA_NAME.equals(criteria)) || (Constants.OPERATION_CRITERIA_TYPE_NAME.equals(criteria))) {
			return productDao.searchByCriteria(criteria, Constants.OPERATION_PLACEHOLDER_LIKE + value.toString().toLowerCase() + Constants.OPERATION_PLACEHOLDER_LIKE, operation, true);
		} else if (Constants.OPERATION_CRITERIA_TYPE_ID.equals(criteria))  {
			return productDao.searchByCriteria(criteria, new Long(value.toString()), operation, true);
		} else if (Constants.OPERATION_CRITERIA_QUANTITY.equals(criteria)) {
			Integer integerValue = null;
			try {
				integerValue = Integer.parseInt(value.toString());
			} catch (NumberFormatException nfe) {
				systemlog.error("Cannot parse value Quantity to Integer: " + value);
			}
			return productDao.searchByCriteria(criteria, integerValue , operation, true);
		} else if (Constants.OPERATION_CRITERIA_ACTIVE.equals(criteria)) {
			String valueString = value.toString();
			if ("1".equals(valueString) || "true".equalsIgnoreCase(valueString)) {
				value = new Boolean(true);
			} else if ("0".equals(valueString) || "false".equalsIgnoreCase(valueString)) {
				value = new Boolean(false);
			}
			return productDao.searchByCriteria(criteria, value , operation, false);
			//return productDao.getAllProducts();
		} else {
			BigDecimal bigDecimalValue = null;
			try {
				bigDecimalValue = new BigDecimal(value.toString());
			} catch (NumberFormatException nfe) {
				systemlog.error("Cannot parse value Price to BigDecimal: " + value);
			}
			return productDao.searchByCriteria(criteria, bigDecimalValue , operation, true);
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

	@Override
	public boolean activateProduct(Product product) {
		product.setActive(Boolean.TRUE);
		return productDao.activateProduct(product);
	}

	@Override
	public boolean deactivateProduct(Product product) {
		product.setActive(Boolean.FALSE);
		return productDao.deactivateProduct(product);
	}

	@Override
	public List<String> validateProduct(Product product) {
		List<String> errors = new ArrayList<>();
		if (product == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Product"));
		if ((product.getCurrency() == null)  || (product.getCurrency().getRate() == null)) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Currency"));
		if (product.getName() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Name"));
		if (product.getQuantity() == 0) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Quantity"));
		if (product.getSinglePrice() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Price"));
		if (product.getType() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Type"));
		return errors;
	}
}
