package com.softuni.webstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.Role;

@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Role getRoleById(long id) {
		Role role = null;
		try {
			role = em.find(Role.class, id);
		} catch (Exception e) {
			systemlog.error("Cannot find role in database"  + id);
		}
		return role;
	}

	@Override
	public Role getRoleByName(String name) {
		TypedQuery<Role> q;
		
		q = em.createQuery("SELECT o FROM Role o where o.name = :name", Role.class);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

}
