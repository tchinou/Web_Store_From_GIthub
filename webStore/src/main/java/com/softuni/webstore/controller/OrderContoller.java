package com.softuni.webstore.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.webstore.constants.Constants;
import com.softuni.webstore.entity.Customer;
import com.softuni.webstore.entity.Order;
import com.softuni.webstore.entity.OrderDetails;
import com.softuni.webstore.log4j.LoggerManager;
import com.softuni.webstore.service.CustomerService;
import com.softuni.webstore.service.OrderDetailsService;
import com.softuni.webstore.service.OrderService;
import com.softuni.webstore.service.OrderTypeService;
import com.softuni.webstore.service.ProductService;
import com.softuni.webstore.utility.UserUtils;

@Controller
public class OrderContoller extends BaseController{
	Logger systemLogger = LoggerManager.getSystemLogger();
	Logger userLogger = LoggerManager.getUserLogger();
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderTypeService orderTypeService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MessageSource message;
	
	/**
	 * This method add items to Cart
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(Model model,  @RequestParam long productId, HttpServletRequest request) {
		Order order = getOrder();
		order.getOrderDetails().add(orderDetailsService.addProductToCart(productService.getProductById(productId), order));
		order.setTotalPrice(orderService.calculateTotalPrice(order));
		order.setTotalQuantity(orderService.calculateTotalQuantity(order));
		model.addAttribute(order);
		getSession().setAttribute("order", order);
		return new ModelAndView("cart", "order", order) ;
		
		//Edited from server
	}
	
	
	@RequestMapping(value="cart", method=RequestMethod.GET)
	public ModelAndView showCart (Model model, HttpServletRequest request) {
		return new ModelAndView("cart", "order", getOrder());
	}
	
	@Transactional  
	@RequestMapping(value="processOrder", method=RequestMethod.POST)
	public ModelAndView processOrder(HttpServletRequest request) {
		Order order = getOrder();
		if (order == null) return new ModelAndView("login");
		order.setComment(request.getParameter("order.comment"));
		
		Customer customer = customerService.getCustomerByUsername(UserUtils.getUser().getUsername());

		if (customer == null) return new ModelAndView("login");
		else {
			order.setCustomer(customer);
		}
		
		fillOrderDetails(order, request);
		
		if (orderService.addOrder(order)) {
			getSession().removeAttribute("session");
			getSession().invalidate();
			return new ModelAndView("cart").addObject("msg", message.getMessage("order.success", null, getLocale()));
		} else {
			return new ModelAndView("cart");
		}
	}
	
	@RequestMapping(value="deleteProductFromCart", method=RequestMethod.GET)
	public String removeProductFromCart(@RequestParam int rowIndex) {
		Order order = getOrder();
		order.getOrderDetails().remove(rowIndex);
		order.setTotalPrice(orderService.calculateTotalPrice(order));
		order.setTotalQuantity(orderService.calculateTotalQuantity(order));
		return "cart";
	}	

	private void fillOrderDetails(Order order, HttpServletRequest request) {
		order.setOrderType(orderTypeService.getOrderTypeByName(Constants.ORDER_TYPE_SELL));
		order.setPurchaseDate(new Date());
	}
	
	@RequestMapping(value="order_table", method=RequestMethod.GET)
	public ModelAndView showOrderTable (Model model, HttpServletRequest request) {
		return new ModelAndView("order_table", "orders", orderService.getOrders());
	}
	
	@Transactional
	@RequestMapping(value="order_refund", method=RequestMethod.GET)
	public ModelAndView refundOrder (@RequestParam ("id") long id) {
		Order originalOrder = orderService.getOrderById(id); 
		if (!Constants.ORDER_TYPE_SELL.equals(originalOrder.getOrderType().getName())) {
			return new ModelAndView("order_table", "orders", orderService.getOrders()).addObject("msg",  message.getMessage("order.refund.alreadyRefunded", null, getLocale()));
		} 
		originalOrder.setOrderType(orderTypeService.getOrderTypeByName(Constants.ORDER_TYPE_REFUNDED));

		orderService.addOrder(orderService.generateRefundOrder(originalOrder));
		orderService.editOrder(originalOrder);
		return new ModelAndView("order_table", "orders", orderService.getOrders()).addObject("msg", message.getMessage("order.refund.success", null, getLocale()));
	}
	
	@RequestMapping(value="performOrderSearch", method=RequestMethod.GET)
	public ModelAndView performProductSearch( @RequestParam ("criteriaGroup") String criteria,  @RequestParam ("criteriaValue") String value, @RequestParam ("operation") String operation , HttpServletRequest request) {
		if (value == null) value = "";
		return new ModelAndView("order_table", "orders", orderService.searchByCriteria(criteria, value.trim(), operation));
	}
	
	@RequestMapping(value="order_view", method=RequestMethod.GET)
	public ModelAndView showOrderView (@RequestParam int id) {
		return new ModelAndView("order_view", "orderDetails", orderDetailsService.getOrderDetailsByOrderId(id));
	}
}
