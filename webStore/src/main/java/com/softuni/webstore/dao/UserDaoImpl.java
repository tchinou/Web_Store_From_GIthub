package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.softuni.webstore.entity.User;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public User getUserById(long id) {
		User user = null;
		try {
			user = em.find(User.class, id);
			userlog.debug("User was found in database: " + user);
			return user;
		} catch (Exception e) {
			userlog.error("Cannot find User: " + user);
			systemlog.error("Cannot find User: " + e.getMessage());
			return null;
		}
	}

	@Override
	public User getUserByName(String name) {
		TypedQuery<User> q;
		
		q = em.createQuery("SELECT o.user FROM Customer o WHERE o.user.username = :name and o.active = 1 ORDER BY o.id", User.class);
		q.setParameter("name", name);
		return getSingleResult(q);
	}

	@Override
	public List<User> getUsersByRole(String roleName) {
		TypedQuery<User> q;
		
		q = em.createQuery("SELECT o FROM User o WHERE o.role.name = :roleName ORDER BY o.id", User.class);
		q.setParameter("roleName", roleName);
		return q.getResultList();
	}

}
