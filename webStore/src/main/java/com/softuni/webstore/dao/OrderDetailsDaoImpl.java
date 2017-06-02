package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.OrderDetails;
import com.softuni.webstore.log4j.LoggerManager;

@Repository
public class OrderDetailsDaoImpl extends BaseDao implements OrderDetailsDao{

	Logger userlog = LoggerManager.getUserLogger();
	Logger systemlog = LoggerManager.getSystemLogger();

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public OrderDetails getOrderDetailsByProductId(long id) {
		TypedQuery<OrderDetails> q;
		
		q = em.createQuery("SELECT o FROM OrderDetails o WHERE o.product.id = :id", OrderDetails.class);
		q.setParameter("id", id);
		return getSingleResult(q);
	}

	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(long id) {
		TypedQuery<OrderDetails> q;
		
		q = em.createQuery("SELECT o FROM OrderDetails o WHERE o.order.id = :id", OrderDetails.class);
		q.setParameter("id", id);
		return q.getResultList();
	}
}
