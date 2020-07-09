package com.subg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rating_id;
	private int rating;
	private String comment;
	private long user_id;
	private long product_id;
	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name="user_id",nullable=false,updatable=false,insertable=false)
	private Users user;
	
	@ManyToOne(targetEntity = Products.class)
	@JoinColumn(name = "product_id",nullable=false,updatable=false,insertable=false)
	private Products products;

	public long getRating_id() {
		return rating_id;
	}

	public void setRating_id(long rating_id) {
		this.rating_id = rating_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
	
}
