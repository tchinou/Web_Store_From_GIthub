package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Product;
import com.softuni.webstore.log4j.LoggerManager;

@Repository
public class ProductDaoImpl extends BaseDao implements ProductDao{
	
	Logger userlog = LoggerManager.getUserLogger();
	Logger systemlog = LoggerManager.getSystemLogger();
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean addProduct(Product product) {
		try {
			em.persist(product);
			userlog.debug("Product was added successful: " + product);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot add product: " + product);
			systemlog.error("Cannot add product: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean editProduct(Product product) {
		try {
			em.merge(product);
			userlog.debug("Product was edited successful: " + product);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot edit product: " + product);
			systemlog.error("Cannot edit product: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteProduct(long id) {
		Product product = null;
		try {
			product = em.find(Product.class, id);
			userlog.debug("Product was deleted successful: " + product);
			em.remove(product);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot delete product: " + product);
			systemlog.error("Cannot delete product: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Product> searchByCriteria(String criteria, Object value, String operation, boolean showActive) {
		TypedQuery<Product> q;
		String showActiveSQL = showActive ? "AND o.active = 1" : "";
		try {
			q = em.createQuery("SELECT o FROM Product o WHERE lower(o."+ criteria + ") " + operation + " :value " + showActiveSQL + " ORDER BY o.id", Product.class);
			q.setParameter("value", value);
			userlog.debug("search by criteria: " + criteria + ", and value: " + value + " is succesfull");
			return q.getResultList();
		} catch (Exception e) {
			userlog.error("Cannot search by criteria: " + criteria + ", and value: " + value);
			userlog.error("Cannot search by criteria: " + criteria + ", value: " + value + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		TypedQuery<Product> q;
		
		try {
			q = em.createQuery("SELECT o FROM Product o ORDER BY o.id", Product.class);
			return q.getResultList();
		} catch (Exception e) {
			systemlog.error("Cannot getAllProducts: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Product getProductById(Long id) {
		Product product = null;
		try {
			product = em.find(Product.class, id);
			userlog.debug("Product was loaded successful: " + product);
			return product;
		} catch (Exception e) {
			userlog.error("Cannot find product: " + product);
			systemlog.error("Cannot find product: " + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean activateProduct(Product product) {
		try {
			em.merge(product);
			userlog.debug("Product was activated successful: " + product);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot activate product: " + product);
			systemlog.error("Cannot activate product: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deactivateProduct(Product product) {
		try {
			em.merge(product);
			userlog.debug("Product was deactivated successful: " + product);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot deactivate product: " + product);
			systemlog.error("Cannot deactivate product: " + e.getMessage());
			return false;
		}
	}

}
