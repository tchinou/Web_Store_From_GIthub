package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.Customer;

public interface CustomerDao {
	public boolean addCustomer(Customer customer);
	public boolean editCustomer (Customer customer);
	public boolean deleteCustomer(long id);
	public Customer getCustomerById (long id);
	public Customer getCustomerByUsername(String username);
	public boolean activateCustomer (Customer customer);
	public boolean deactivateCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public List<Customer> searchByCriteria(String criteria, Object value, String operation);
}
