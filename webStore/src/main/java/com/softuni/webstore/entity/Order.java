package com.softuni.webstore.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_order")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class Order {
	@Id
	@Column(name="id")
	@TableGenerator(name="TABLE_GEN_ORDER",table="T_GENERATOR", pkColumnName = "GEN_KEY", pkColumnValue = "WEBSTORE.T_ORDER", valueColumnName = "GEN_VALUE", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TABLE_GEN_ORDER")
	private long id;
	
	@OneToMany (cascade=javax.persistence.CascadeType.REFRESH, mappedBy="order")
	private List<OrderDetails> orderDetails;
	
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="order_type_id")
	private OrderType orderType;
	
	@ManyToOne(optional=false, cascade={CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="purchase_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Column(name="total_price")
	private BigDecimal totalPrice;
	
	@Column(name="\"COMMENT\"")
	private String comment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<OrderDetails> getOrderDetails() {
		if (orderDetails == null) {
			orderDetails = new ArrayList<>();
		}
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Order id:" + getId() +  ", order type: " + getOrderType() + 
				", customer: " + getCustomer()  + ", purchase date: " + getPurchaseDate() +
				", total price: " + getTotalPrice() + ", comment: " + getComment(); 
	}
}
