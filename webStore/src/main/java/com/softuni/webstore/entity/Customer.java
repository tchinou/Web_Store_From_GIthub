package com.softuni.webstore.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_customer")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class Customer {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_CUSTOMER",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_CUSTOMER", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_CUSTOMER")
	private long id;
	
	@NotNull
	@Size(min=4, max=40)
	@Column(name="name")
	private String name;
	
	@Column(name="birth_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date birthDate;
	
	@NotNull
	@Size(min=1, max=90)
	@Column(name="address")
	private String address;
	
	@Valid
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="active")
	private boolean active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Customer id: " + getId() + ", name: " + getName() + ", birth date: " + getBirthDate() +
				", address: " + getAddress() + ", user: " + getUser() + ", active: " + getActive();
	}
}
