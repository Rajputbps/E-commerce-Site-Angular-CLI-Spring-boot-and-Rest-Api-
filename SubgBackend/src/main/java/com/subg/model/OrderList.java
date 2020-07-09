package com.subg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 

@Entity
public class OrderList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderList_Id;
	private long Product_Quantity;
	private long product_id;
	
	
	
	@ManyToOne(targetEntity = Products.class)
	@JoinColumn(name = "product_id",nullable=false,updatable=false,insertable=false)
	private Products products;
	
	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}


	private long orderId;
 
	
	public long getOrderList_Id() {
		return orderList_Id;
	}
 
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setOrderList_Id(long orderList_Id) {
		this.orderList_Id = orderList_Id;
	}


	public long getProduct_Quantity() {
		return Product_Quantity;
	}


	public void setProduct_Quantity(long product_Quantity) {
		Product_Quantity = product_Quantity;
	}

	
	
	
}
