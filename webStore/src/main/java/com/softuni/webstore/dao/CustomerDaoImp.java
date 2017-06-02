package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Customer;
import com.softuni.webstore.log4j.LoggerManager;

@Repository
public class CustomerDaoImp extends BaseDao implements CustomerDao {
	Logger userlog = LoggerManager.getUserLogger();
	Logger systemlog = LoggerManager.getSystemLogger();

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean addCustomer(Customer customer) {
		try {
			em.persist(customer.getUser());
			em.persist(customer);
			userlog.debug("Customer was added successful: " + customer);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot add customer: " + customer);
			systemlog.error("Cannot add customer: " + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean editCustomer(Customer customer) {
		try {
			em.merge(customer);
			userlog.debug("customer was edited successful: " + customer);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot edit customer: " + customer);
			systemlog.error("Cannot edit customer: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean deleteCustomer(long id) {
		Customer customer = null;
		try {
			customer = em.find(Customer.class, id);
			em.remove(customer);
			return true;
		} catch (Exception e) {
			systemlog.error("Cannot remove customer with id: " + id + e.getLocalizedMessage());
			return false;
		}
	}
	
	@Override
	public Customer getCustomerById(long id) {
		Customer customer = null;
		try {
			customer = em.find(Customer.class, id);
		} catch (Exception e) {
			systemlog.error("Cannot find customer with id: " + id );
		}
		return customer;
	}
	
	@Override
	public Customer getCustomerByUsername(String username) {
		TypedQuery<Customer> q;
		
		q = em.createQuery("SELECT o FROM Customer o where o.user.username = :username AND o.active = 1", Customer.class);
		q.setParameter("username", username);
		return getSingleResult(q);
	}


	@Override
	public boolean activateCustomer(Customer customer) {
		try {
			em.merge(customer);
			userlog.debug("Customer was activated successful: " + customer);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot activate customer: " + customer);
			systemlog.error("Cannot activate customer: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deactivateCustomer(Customer customer) {
		try {
			em.merge(customer);
			userlog.debug("Customer was deactivated successful: " + customer);
			return true;
		} catch (Exception e) {
			userlog.error("Cannot deactivate customer: " + customer);
			systemlog.error("Cannot deactivate customer: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> q;
		
		q = em.createQuery("SELECT o FROM Customer o WHERE o.user.role.id > 1", Customer.class);
		return q.getResultList();
	}

	@Override
	public List<Customer> searchByCriteria(String criteria, Object value, String operation) {
		TypedQuery<Customer> q;
		
		try {
			q = em.createQuery("SELECT o FROM Customer o WHERE lower(o."+ criteria + ") " + operation + " :value  AND o.user.id > 1  ORDER BY o.id", Customer.class);
			q.setParameter("value", value);
			userlog.debug("search by criteria: " + criteria + ", and value: " + value + " is succesfull");

			return q.getResultList();
		} catch (Exception e) {
			userlog.error("Cannot search by criteria: " + criteria + ", and value: " + value);
			userlog.error("Cannot search by criteria: " + criteria + ", value: " + value + e.getMessage());
			return null;
		}
	}

	

	
}
