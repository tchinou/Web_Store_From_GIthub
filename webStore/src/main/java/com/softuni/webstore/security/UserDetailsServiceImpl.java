package com.softuni.webstore.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softuni.webstore.dao.UserDao;
import com.softuni.webstore.log4j.LoggerManager;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserDao userDao;	
	Logger logger = LoggerManager.getSystemLogger();
	
	/**
	 *  added 2 users in db: username: "admin" role: ROLE_ADMIN password: 111111 ,
	 *  username: "user" role: ROLE_USER password: 222222 
	 */
	List<GrantedAuthority> authorities ; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.softuni.webstore.entity.User dbUser = null;
		authorities = new ArrayList<>();
		if (username == null ) {
			logger.error("Cannot authenticate user:" + username);
			throw new AuthenticationServiceException("Cannot authenticate user:" + username);
		} else {
			dbUser = userDao.getUserByName(username);
			if (dbUser == null) {
				logger.error("Cannot load user from db:" + username);
				throw new AuthenticationServiceException("Cannot load user from db:" + username);
			}
			if (username.equals(dbUser.getUsername())) {
				authorities.add(new SimpleGrantedAuthority(dbUser.getRole().getName()));
			} 
		}
		return new User(username, dbUser.getPassword(), authorities);
	}
}