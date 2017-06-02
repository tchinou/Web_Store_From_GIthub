package com.softuni.webstore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.constants.Constants;
import com.softuni.webstore.dao.CustomerDao;
import com.softuni.webstore.entity.Customer;
import com.softuni.webstore.entity.Role;
import com.softuni.webstore.entity.User;
import com.softuni.webstore.log4j.LoggerManager;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private Logger systemlog = LoggerManager.getSystemLogger();
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public boolean addCustomer(Customer customer) {
		return customerDao.addCustomer(customer);
	}

	@Override
	public boolean editCustomer(Customer customer) {
		return customerDao.editCustomer(customer);
	}
	
	@Override
	public Customer getCustomerById(long id) {
		return customerDao.getCustomerById(id);
	}
	
	@Override
	public boolean deleteCustomer(long id) {
		return customerDao.deleteCustomer(id);
	}

	@Override
	public boolean activate(Customer customer) {
		customer.setActive(true);
		return customerDao.activateCustomer(customer);
	}

	@Override
	public boolean deactivateCustomer(Customer customer) {
		customer.setActive(false);
		return customerDao.deactivateCustomer(customer);
	}

	@Override
	public List<Customer> searchCriteria(String criteria, Object value, String operation) {
		if ((Constants.OPERATION_CRITERIA_NAME.equals(criteria)) || (Constants.OPERATION_CRITERIA_USERNAME.equals(criteria))) {
			return customerDao.searchByCriteria(criteria, Constants.OPERATION_PLACEHOLDER_LIKE + value.toString().toLowerCase() + Constants.OPERATION_PLACEHOLDER_LIKE, operation);
		} else if (Constants.OPERATION_CRITERIA_BIRTHDATE.equals(criteria))  {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(value.toString());
				if (Constants.OPERATION_LIKE.equals(operation)) {
					operation = Constants.OPERATION_EQUALS;
				}
				return customerDao.searchByCriteria(criteria, date , operation);
			} catch (ParseException e) {
				systemlog.error("Cannot parse Date:" + e.getMessage());
				return customerDao.searchByCriteria(criteria, value , operation);
			}
		} else if (Constants.OPERATION_CRITERIA_ACTIVE.equals(criteria)) {
			String valueString = value.toString();
			if ("1".equals(valueString) || "true".equalsIgnoreCase(valueString)) {
				value = new Boolean(true);
			} else if ("0".equals(valueString) || "false".equalsIgnoreCase(valueString)) {
				value = new Boolean(false);
			}
			return customerDao.searchByCriteria(criteria, value , operation);
		} else {
			return customerDao.searchByCriteria(criteria, value , operation);
		}
	}

	@Override
	public List<String> validateCustomer(Customer customer) {
		List<String> errors = new ArrayList<>();
		if (customer == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Customer"));
		//if (customer.getBirthDate() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Birth Date"));
		if (customer.getName() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Name"));
		if (customer.getUser() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "User"));
		
		errors.addAll(validateUser(customer.getUser()));
		
		return errors;
	}

	@Override
	public List<String> validateUser(User user) {
		List<String> errors = new ArrayList<>();
		if (user.getUsername() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "Username"));
		if (user.getPassword() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "User Password"));
		if (user.getRole() == null) errors.add(Constants.ERROR_MANDATORY.replace("{0}", "User Role"));
		
		return errors;
	}

	@Override
	public boolean addRole(Customer customer, Role role) {
		customer.getUser().setRole(role);
		return true;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return customerDao.getCustomerByUsername(username);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}
}
  