package com.softuni.webstore.service;

import com.softuni.webstore.entity.Role;

public interface RoleService {
	public Role getRoleById(long id);
	public Role getRoleByName(String name);
}
