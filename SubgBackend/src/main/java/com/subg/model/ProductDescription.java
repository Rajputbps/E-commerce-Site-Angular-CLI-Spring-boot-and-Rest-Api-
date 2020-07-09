package com.subg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 

@Entity
public class ProductDescription {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long description_Id;
	private String Vitamins;
	private String Benefits;
	private long productId;
	
//	@ManyToOne(targetEntity=Products.class)
//	@JoinColumn(name="productId",nullable=false,updatable=false,insertable=false )
//	 private Products products;
	
	public Long getDescription_Id() {
		return description_Id;
	}
	public void setDescription_Id(Long description_Id) {
		this.description_Id = description_Id;
	}
	public String getVitamins() {
		return Vitamins;
	}
	public void setVitamins(String vitamins) {
		Vitamins = vitamins;
	}
	public String getBenefits() {
		return Benefits;
	}
	public void setBenefits(String benefits) {
		Benefits = benefits;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
//	public Products getProducts() {
//		return products;
//	}
//	public void setProducts(Products products) {
//		this.products = products;
//	}
	
	
}
