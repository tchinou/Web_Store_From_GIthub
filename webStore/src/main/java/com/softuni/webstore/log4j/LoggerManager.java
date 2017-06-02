package com.softuni.webstore.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softuni.webstore.constants.Constants;

public class LoggerManager {
	public static Logger getSystemLogger() {
		return LoggerFactory.getLogger(Constants.LOGGER_SYSTEM);
	}
	
	public static Logger getUserLogger() {
		return LoggerFactory.getLogger(Constants.LOGGER_USER);
	}
}
