package com.softuni.webstore.entity;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Table(name="t_user")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class User {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_USER",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_USER", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_USER")
	private long id;
	
	@NotNull
	@Size (min=1, max=20)
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size (min=1, max=50)
	@Column(name="password")
	private String password;
	
	@Transient
	private String retypePassword;
	
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="role_id")
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User id:" + getId() + ", username: " + getUsername() + ", role: " + getRole();
	}
}
