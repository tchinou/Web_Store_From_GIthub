package com.softuni.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.softuni.webstore.log4j.LoggerManager;

public abstract class BaseDao {
	protected Logger userlog = LoggerManager.getUserLogger();
	protected Logger systemlog = LoggerManager.getSystemLogger();

	public <T> T getSingleResult(TypedQuery<T> q) {
		List<T> lst;

		lst = q.getResultList();
		if (lst.size() == 0)
			return null;
		else
			return lst.get(0);
	}
}
