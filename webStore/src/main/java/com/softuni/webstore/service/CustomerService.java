package com.softuni.webstore.service;

import java.util.List;

import com.softuni.webstore.entity.Customer;
import com.softuni.webstore.entity.Role;
import com.softuni.webstore.entity.User;

public interface CustomerService {
	public boolean addCustomer(Customer customer);
	public boolean editCustomer (Customer customer);
	public boolean deleteCustomer(long id);
	public Customer getCustomerById (long id);
	public Customer getCustomerByUsername(String username);
	public boolean activate (Customer customer);
	public boolean deactivateCustomer(Customer customer);
	public List<Customer> searchCriteria(String criteria, Object value, String operation);
	public List<Customer> getAllCustomers();
	public List<String> validateCustomer(Customer customer);
	public List<String> validateUser(User user);
	public boolean addRole(Customer customer, Role role);
}
