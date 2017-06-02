package com.softuni.webstore.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.softuni.webstore.security.User;

public class UserUtils {
	private UserUtils() {}
	
	public static User getUser() {
		Object principal;
		
		try {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (NullPointerException npe) {
			return null;
		}
		
		if (!(principal instanceof User)) return null;
		return (User) principal;
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null){    
             new SecurityContextLogoutHandler().logout(request, null, null);
          }
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
}
