package com.subg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long img_id;
	private String img_url;
	private long product_id;
	
	@JsonIgnore
	@OneToOne(targetEntity = Products.class)
	@JoinColumn(name="product_id",nullable=false,updatable=false,insertable=false)
	private Products  products;

	public long getImg_id() {
		return img_id;
	}

	public void setImg_id(long img_id) {
		this.img_id = img_id;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
	
	
}
