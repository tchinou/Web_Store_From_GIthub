package com.softuni.webstore.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="t_currency")
public class Currency {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_CURRENCY",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_CURRENCY", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_CURRENCY")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="rate")
	private BigDecimal rate;
	
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
