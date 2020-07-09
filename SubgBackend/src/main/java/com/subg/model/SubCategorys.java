package com.subg.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubCategorys {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long subcategoryId;
	private Long categoryId;
	private String subCateName;
	private String subCatDesc;
	
	
	@OneToMany(mappedBy="subcategoryId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Products> products;
	
//	@JsonIgnore
//	@ManyToOne(targetEntity=Categorys.class)
//	@JoinColumn(name="categoryId",nullable=false,updatable=false,insertable=false )
//	 private Categorys category;
	
	public SubCategorys() {
		 
	}

	 

	public Long getSubcategoryId() {
		return subcategoryId;
	}



	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}



	public Long getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}



	public String getSubCateName() {
		return subCateName;
	}

	public void setSubCateName(String subCateName) {
		this.subCateName = subCateName;
	}

	public String getSubCatDesc() {
		return subCatDesc;
	}

	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}

	public Set<Products> getProducts() {
		return products;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}

//	public Categorys getCategory() {
//		return category;
//	}
//
//	public void setCategory(Categorys category) {
//		this.category = category;
//	}
	
	

}
