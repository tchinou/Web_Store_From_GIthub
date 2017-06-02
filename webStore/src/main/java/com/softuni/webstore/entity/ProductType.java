package com.softuni.webstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.softuni.webstore.utility.StringUtils;

@Entity
@Table(name="t_product_type")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class ProductType {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_PRODUCT_TYPE",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_PRODUCT_TYPE", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_PRODUCT_TYPE")
	private long id;
	
	@Column(name="name")
	private String name;
	
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
	
	@Override
	public String toString() {
		return StringUtils.capEachWord(getName());
	}
}
