package com.softuni.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	@RequestMapping(value="maps", method=RequestMethod.GET)
	public ModelAndView showCart () {
		return new ModelAndView("maps");
	}
	
	@RequestMapping(value="faq", method=RequestMethod.GET)
	public ModelAndView showFaq () {
		return new ModelAndView("faq");
	}
	
	@RequestMapping(value="contact", method=RequestMethod.GET)
	public ModelAndView showContact () {
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value="rss", method=RequestMethod.GET)
	public ModelAndView showRss () {
		return new ModelAndView("rss");
	}
}
