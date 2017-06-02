package com.softuni.webstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Table(name="t_role")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class Role {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Role id:" + getId() + ", name: " + getName();
	}
}
