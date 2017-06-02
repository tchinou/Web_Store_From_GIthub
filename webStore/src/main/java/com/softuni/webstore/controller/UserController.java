package com.softuni.webstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.webstore.constants.Constants;
import com.softuni.webstore.entity.Customer;
import com.softuni.webstore.log4j.LoggerManager;
import com.softuni.webstore.security.MD5;
import com.softuni.webstore.security.User;
import com.softuni.webstore.service.CustomerService;
import com.softuni.webstore.service.OrderService;
import com.softuni.webstore.service.RoleService;
import com.softuni.webstore.utility.UserUtils;

@Controller
public class UserController extends BaseController{
	Logger userLogger = LoggerManager.getUserLogger();
	Logger systemLogger = LoggerManager.getSystemLogger();
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MessageSource message;
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("customer") Customer customer) {
		return new ModelAndView("register", "customer", customer);
	}
	
	@Transactional
	@RequestMapping(value="doRegister", method = RequestMethod.POST)
	public ModelAndView doRegister(@Valid @ModelAttribute ("customer") Customer customer, BindingResult result,  @RequestParam ("id") long id,  HttpServletRequest request) {
		customerService.addRole(customer, roleService.getRoleByName(Constants.ROLE_USER));
		
		if (!result.hasErrors()) {
			if (id == 0)  {
				ModelAndView model = new ModelAndView("register");
				if (customer.getUser().getUsername() == null || "".equals(customer.getUser().getUsername())) model.addObject("msg", message.getMessage("account.error.empty.username", null, getLocale()));
				if (!customer.getUser().getPassword().equals(customer.getUser().getRetypePassword())) model.addObject("msg", message.getMessage("account.error.password", null, getLocale()));
				if (customerService.getCustomerByUsername(customer.getUser().getUsername()) != null) model.addObject("msg", message.getMessage("account.error.username.used", null, getLocale()));
				
				if (model.getModelMap().size() > 0 && model.getModelMap().get("msg") != null) {
					return model;
				}
				customer.getUser().setPassword(MD5.crypt(customer.getUser().getPassword()));
				customerService.addCustomer(customer);
				customerService.activate(customer);
			} else  {
				customer.getUser().setPassword(customerService.getCustomerById(id).getUser().getPassword());
				customer.getUser().setRetypePassword(customerService.getCustomerById(id).getUser().getRetypePassword());
				customerService.editCustomer(customer);
			}
			return new ModelAndView("login").addObject("msg", message.getMessage("account.register.success", null, getLocale()));
		} else {
			if (id == 0) {
				if (result.getFieldError("birthDate") != null) {
					return register(customer).addObject("dateError", message.getMessage("account.error.date", null, getLocale()));
				}
				return register(customer);
			} 
			return accountEdit().addObject("msg", message.getMessage("account.error.notsuccess", null, getLocale()));
		}
	}
	
	@RequestMapping(value="account_table", method = RequestMethod.GET)
	public ModelAndView customer_table() {
		List<Customer> accounts = customerService.getAllCustomers();
		ModelAndView model = new ModelAndView("account_table", "accounts", accounts);
		return model;
	}
	
	@RequestMapping(value="account_edit", method = RequestMethod.GET)
	public ModelAndView accountsEdit(@RequestParam long id) {
		Customer customer = customerService.getCustomerById(id);
		customer.getUser().setRetypePassword(customer.getUser().getPassword());
		ModelAndView model = new ModelAndView("register", "customer", customer);
		return model;
	}
	
	@RequestMapping(value="account", method = RequestMethod.GET)
	public ModelAndView accountEdit() {
		User user = UserUtils.getUser();
		Customer customer = customerService.getCustomerByUsername(user == null ? null : user.getUsername());
		if (customer != null) {
			customer.getUser().setRetypePassword(customer.getUser().getPassword());
		}
		return new ModelAndView("register", "customer", customer);
	}
	
	@RequestMapping(value="account_change_password", method = RequestMethod.GET)
	public ModelAndView accountChangePassword(@RequestParam ("id") long id) {
		Customer customer = customerService.getCustomerById(id);
		customer.getUser().setPassword(null);
		return new ModelAndView("account_change_password", "customer", customer);
	}
	
	@Transactional
	@RequestMapping(value="do_account_change_password", method = RequestMethod.POST)
	public ModelAndView doAccountChangePassword(@ModelAttribute ("customer") Customer customer,
			@RequestParam ("id") long id, HttpServletRequest request, HttpServletResponse response) {
		if (customer.getUser() == null || "".equals(customer.getUser().getPassword())) return new ModelAndView("account_change_password").addObject("msg", message.getMessage("account.error.empty.password", null, getLocale()));
		if (customer.getUser() == null || "".equals(customer.getUser().getRetypePassword())) return new ModelAndView("account_change_password").addObject("msg", message.getMessage("account.error.empty.retypePassword", null, getLocale()));
		if (!customer.getUser().getPassword().equals(customer.getUser().getRetypePassword())) return new ModelAndView("account_change_password").addObject("msg", message.getMessage("account.error.password", null, getLocale())); 
		Customer dbCustomer = customerService.getCustomerById(id);
		dbCustomer.getUser().setPassword(MD5.crypt(customer.getUser().getPassword()));
		dbCustomer.getUser().setRetypePassword(dbCustomer.getUser().getPassword());
		if (customerService.editCustomer(dbCustomer)) {
			setNullPasswordOfUser(customer);
			getSession().invalidate();
			UserUtils.logout(request, response);;
			return new ModelAndView("redirect:/login?logout=true").addObject("msg", message.getMessage("action.success", null, getLocale()));
		} else {
			setNullPasswordOfUser(customer);
			return new ModelAndView("account_change_password", "customer", customer).addObject("msg", message.getMessage("action.unsuccess", null, getLocale()));
		}
	}
	
	@RequestMapping(value="performAccountSearchAdmin", method=RequestMethod.GET)
	public ModelAndView performProductSearchAdmin( @RequestParam ("criteriaGroup") String criteria,  @RequestParam ("criteriaValue") String value, @RequestParam ("operation") String operation , HttpServletRequest request) {
		if (value == null) value = "";
		return new ModelAndView("account_table", "accounts", customerService.searchCriteria(criteria, value, operation));
	}
	
	public void setNullPasswordOfUser(Customer customer) {
		customer.getUser().setPassword("");
		customer.getUser().setRetypePassword("");
	}
	
	@Transactional
	@RequestMapping(value="account_delete", method = RequestMethod.GET)
	public ModelAndView accountDelete(@RequestParam ("id") long id) {
		if (orderService.getOrderByCustomerId(id) != null) {
			return customer_table().addObject("msg", message.getMessage("account.admin.delete.notsuccess", null, getLocale()));
		}
		if (customerService.deleteCustomer(id)) {
			return customer_table().addObject("msg", message.getMessage("account.admin.delete.success", null, getLocale()));
		} 
		return customer_table();
	}
}
