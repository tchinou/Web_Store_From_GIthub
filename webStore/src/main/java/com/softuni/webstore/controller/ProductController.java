package com.softuni.webstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.softuni.webstore.entity.Product;
import com.softuni.webstore.log4j.LoggerManager;
import com.softuni.webstore.service.CurrencyService;
import com.softuni.webstore.service.OrderDetailsService;
import com.softuni.webstore.service.ProductService;
import com.softuni.webstore.service.ProductTypeService;

@Controller
public class ProductController extends BaseController{
	Logger systemLogger = LoggerManager.getSystemLogger();
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	ProductTypeService productTypeService;
	
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@Autowired
	MessageSource message;
	
	@RequestMapping(value="home", method = RequestMethod.GET)
	public ModelAndView loadProducts() {
		return  new ModelAndView("home", "products", productService.searchByCriteria(Constants.OPERATION_CRITERIA_NAME, "", Constants.OPERATION_LIKE));
	}
	
	@RequestMapping(value="product_details", method = RequestMethod.GET)
	public ModelAndView loadProductsDetails(@RequestParam long id) {
		return new ModelAndView("product_details", "product", productService.getProductById(id));
	}
	
	@RequestMapping(value="performProductSearch", method=RequestMethod.GET)
	public ModelAndView performProductSearch( @RequestParam ("criteriaGroup") String criteria,  @RequestParam ("criteriaValue") String value, @RequestParam ("operation") String operation , HttpServletRequest request) {
		if (value == null) value = "";
		return new ModelAndView("home", "products", productService.searchByCriteria(criteria, value, operation));
	}
	
	@RequestMapping(value="performProductSearchAdmin", method=RequestMethod.GET)
	public ModelAndView performProductSearchAdmin( @RequestParam ("criteriaGroup") String criteria,  @RequestParam ("criteriaValue") String value, @RequestParam ("operation") String operation , HttpServletRequest request) {
		if (value == null) value = "";
		return new ModelAndView("product_table", "products", productService.searchByCriteria(criteria, value, operation));
	}
	
	@RequestMapping(value="product_table", method = RequestMethod.GET)
	public ModelAndView product_table() {
		List<Product> products = productService.getAllProducts();
		ModelAndView model = new ModelAndView("product_table", "products", products);
		return model;
	}
	
	@RequestMapping(value="product_edit", method = RequestMethod.GET)
	public ModelAndView editProducts(@RequestParam long id) {
		Product product = productService.getProductById(id);
		product.setTypes(productTypeService.getProductTypes());
		ModelAndView model = new ModelAndView("product", "product", product);
		return model;
	}
	
	@Transactional
	@RequestMapping(value="do_product", method = RequestMethod.POST)
	public ModelAndView doEditProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, @RequestParam ("id") long id,  @RequestParam ("currency.id") long currencyId,  @RequestParam ("type.id") long typeId, HttpServletRequest request) {
		product.setCurrency(currencyService.getCurrencyById(currencyId));
		product.setType(productTypeService.getProductTypeById(typeId));
		product.setTypes(productTypeService.getProductTypes());
		
		if (result.hasErrors())  return new ModelAndView("product", "product", product);
		ModelAndView model = new ModelAndView("product", "product", null);
		if (id == 0)  {
			product.setPictureName("pic1.jpg");
			productService.addProduct(product);
			return model.addObject("msg", message.getMessage("product.create.success", null, getLocale()));
		} else {
			productService.editProduct(product);
			return model.addObject("msg", message.getMessage("product.edit.success", null, getLocale()));
		}
	}
	
	@Transactional
	@RequestMapping(value="product_create", method = RequestMethod.GET)
	public ModelAndView createProduct(@ModelAttribute("product") Product product) {
		product.setCurrency(currencyService.getCurrencyById(1l));
		product.setType(productTypeService.getProductTypeById(1));
		product.setTypes(productTypeService.getProductTypes());
		return new ModelAndView("product", "product", product);
	}
	
	@Transactional
	@RequestMapping(value="product_delete", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam long id) {
		if (orderDetailsService.getOrderDetailsByProductId(id) != null) {
			return product_table().addObject("msg", message.getMessage("account.admin.delete.notsuccess", null, getLocale()));
		}
		if (productService.deleteProduct(id)) {
			return product_table().addObject("msg", message.getMessage("account.admin.delete.success", null, getLocale()));
		} 
		return product_table();
	}
}
