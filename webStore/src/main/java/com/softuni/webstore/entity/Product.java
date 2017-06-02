package com.softuni.webstore.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.softuni.webstore.utility.StringUtils;

@Entity
@Table(name="t_product")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class Product {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_PRODUCT",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_PRODUCT", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_PRODUCT")
	private long id;
	
	@Size (min=4, max=50)
	@Column(name="name")
	private String name;
	
	@Size (min=4, max=400)
	@Column(name="description")
	private String description;
	
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="product_type_id")
	private ProductType type;
	
	@Transient
	private List<ProductType> types;
	
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="currency_id")
	private Currency currency;
	
	@NotNull
	@Min(value=0)
	@Column(name="single_price")
	private BigDecimal singlePrice;
	
	@Min(value=0)
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="picture_name")
	private String pictureName;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	
	public List<String> getProductTypesList() {
		return Arrays.asList(StringUtils.capEachWord(getType().getName()).split(" "));
	}

	public List<ProductType> getTypes() {
		return types;
	}
	public void setTypes(List<ProductType> types) {
		this.types = types;
	}
	public BigDecimal getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(BigDecimal singlePrice) {
		this.singlePrice = singlePrice;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Product  id: " + getId() + ", name: " + getName() + ", product type: " + getType().getName() + 
				", single price: " + getSinglePrice() + ", quantity: " + getQuantity() + ", pictureName:" + getPictureName() +  ", active: " + getActive();
	}
}
