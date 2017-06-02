package com.softuni.webstore.dao;

import java.util.List;

import com.softuni.webstore.entity.User;

public interface UserDao {
	public User getUserById (long id);
	public User getUserByName(String name);
	public List<User> getUsersByRole(String roleName);
}
