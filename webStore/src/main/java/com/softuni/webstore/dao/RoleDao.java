package com.softuni.webstore.dao;

import com.softuni.webstore.entity.Role;

public interface RoleDao {
	public Role getRoleById(long id);
	public Role getRoleByName(String name);
}
