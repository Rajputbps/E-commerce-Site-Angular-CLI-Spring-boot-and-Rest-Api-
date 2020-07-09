package com.subg.model;

 

 
import javax.persistence.Entity;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cart_id;
	private long product_id;
	private long user_id;
	
	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name = "user_id",nullable = false,updatable = false,insertable = false)
	private Users  users;
	
	
	@ManyToOne(targetEntity = Products.class)
	@JoinColumn(name = "product_id",nullable = false,insertable = false,updatable = false)
	private Products products;
	
	
	public Cart() {
		 
	}


	public long getCart_id() {
		return cart_id;
	}


	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}


	public long getProduct_id() {
		return product_id;
	}


	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


	public Products getProducts() {
		return products;
	}


	public void setProducts(Products products) {
		this.products = products;
	}


	 
	
	

}
