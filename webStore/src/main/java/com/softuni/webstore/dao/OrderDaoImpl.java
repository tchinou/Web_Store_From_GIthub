package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Order;
import com.softuni.webstore.entity.OrderDetails;
import com.softuni.webstore.log4j.LoggerManager;

@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao{

	Logger userlog = LoggerManager.getUserLogger();
	Logger systemlog = LoggerManager.getSystemLogger();

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	OrderTypeDao orderTypeDao;
	
	@Override
	public boolean addOrder(Order order) {
		try {
			em.persist(order);
			
			for (OrderDetails orderDetail: order.getOrderDetails()) {
				orderDetail.getProduct().setQuantity(orderDetail.getProduct().getQuantity() - 1);
				em.merge(orderDetail.getProduct());
				em.persist(orderDetail);
			}
			
			userlog.debug("Order was added successful: " + order);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot add order: " + order);
			systemlog.error("Cannot add order: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean editOrder(Order order) {
		try {
			em.merge(order);
			userlog.debug("Order was edited successful: " + order);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot edit order: " + order);
			systemlog.error("Cannot edit order: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public Order getOrderById(long id) {
		Order order = null;
		try {
			order = em.find(Order.class, id);
			userlog.debug("Order was found in database: " + order);
			return order;
		} catch (Exception e) {
			userlog.error("Cannot find Order: " + id);
			systemlog.error("Cannot find Order: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public Order getOrderCustomerId(long id) {
		TypedQuery<Order> q;
		
		q = em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id", Order.class);
		q.setParameter("id", id);
		return getSingleResult(q);
	}
	
	@Override
	public List<Order> getOrdersByProductName(String name) {
		TypedQuery<Order> q;
		
		q = em.createQuery("SELECT o FROM Order o WHERE o.id in (  SELECT det.order.id FROM OrderDetails det WHERE lower(det.product.name) like :name) " , Order.class);
		q.setParameter("name", "%" +  name + "%");
		return q.getResultList();
	}


	@Override
	public List<Order> searchByCriteria(String criteria, Object value, String operation) {
		TypedQuery<Order> q;
		
		try {
			q = em.createQuery("SELECT o FROM Order o WHERE lower (o."+ criteria + ") " + operation + " :value   ORDER BY o.id", Order.class);
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
	public List<Order> getOrders() {
		TypedQuery<Order> q;
		
		q = em.createQuery("SELECT o FROM Order o ORDER BY o.id", Order.class);
		return q.getResultList();
	}

	
	
}
