package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Currency;
import com.softuni.webstore.entity.ProductType;

@Repository
public class ProductTypeDaoImpl extends BaseDao implements ProductTypeDao{
	
	@PersistenceContext 
	EntityManager em;

	@Override
	public ProductType getProductTypeById(long id) {
		ProductType productType = null;
		try {
			productType = em.find(ProductType.class, id);
		} catch (Exception e) {
			systemlog.error("Cannot find ProductType in database"  + id);
		}
		return productType;
	}

	@Override
	public ProductType getProductTypeByName(String name) {
		TypedQuery<ProductType> q;
		
		q = em.createQuery("SELECT o FROM ProductType o where o.name = :name", ProductType.class);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

	@Override
	public List<ProductType> getProductTypes() {
		TypedQuery<ProductType> q;
		
		q = em.createQuery("SELECT o FROM ProductType o", ProductType.class);
		return q.getResultList();
	}

}
