package com.softuni.webstore.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.softuni.webstore.entity.Order;
import com.softuni.webstore.entity.ProductType;
import com.softuni.webstore.service.ProductTypeService;

public abstract class BaseController {
	private Locale locale;
	public static List<ProductType> productTypes;
	
	@Autowired
	ProductTypeService productTypeService;
	
	@PostConstruct
	public void init() {
		productTypes = productTypeService.getProductTypes();
	}
	
	public HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	public Object getAttribute(String name, String defaultValue) {
		Object attribute = getSession().getAttribute(name);
		return (attribute != null) ? attribute : defaultValue ;  
	}
	
	
	public void setAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}
	
	public Order getOrder() {
		Order order = (Order) getSession().getAttribute("order");
		if (order == null) {
			order = new Order();
			getSession().setAttribute("order", order);
		}
		return order;
	}
	
	public Locale getLocale() {
		if (locale == null) {
			locale = new Locale("en", "us");
		}
		return locale;
	}

	public static List<ProductType> getProductTypes() {
		return productTypes;
	}
}
