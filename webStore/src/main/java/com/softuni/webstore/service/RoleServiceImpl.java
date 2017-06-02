package com.softuni.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.webstore.dao.RoleDao;
import com.softuni.webstore.entity.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao roleDao;
	
	@Override
	public Role getRoleById(long id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

}
