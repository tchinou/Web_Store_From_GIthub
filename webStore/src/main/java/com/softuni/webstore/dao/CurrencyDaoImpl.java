package com.softuni.webstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Currency;

@Repository
public class CurrencyDaoImpl extends BaseDao implements CurrencyDao{
	
	@PersistenceContext 
	EntityManager em;
	
	@Override
	public Currency getCurrencyById(long id) {
		Currency currency = null;
		try {
			currency = em.find(Currency.class, id);
		} catch (Exception e) {
			systemlog.error("Cannot find currency in database"  + id);
		}
		return currency;
	}

	@Override
	public Currency getCurrencyByName(String name) {
		TypedQuery<Currency> q;
		
		q = em.createQuery("SELECT o FROM Currency o where o.name = :name WHERE o.active = true", Currency.class);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

}
