package com.subg.model;

 

 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.OneToMany;
  

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 
	private Long productId;
	
	 
	private String product_name;
	 
	private double product_price;
	 
	private int product_unit;
	 
	private Long subcategoryId;
	
	private String imageName;
	
	private Long Category_Id;
	
	 
//	
//	@JsonIgnore
//	@ManyToOne(targetEntity=SubCategorys.class )
//	@JoinColumn(name="subcategoryId",nullable=false,updatable=false,insertable=false)
//	private SubCategorys subCategory;
	
	
	@OneToMany(mappedBy = "productId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ProductDescription> productDescription;
	

	public Set<ProductDescription> getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(Set<ProductDescription> productDescription) {
		this.productDescription = productDescription;
	}


	@OneToMany(mappedBy = "products",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Images> images;
	
 
 

	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}


	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}


	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}


	public int getProduct_unit() {
		return product_unit;
	}

	public void setProduct_unit(int product_unit) {
		this.product_unit = product_unit;
	}

//
//	public SubCategorys getSubCategory() {
//		return subCategory;
//	}
//
//	public void setSubCategory(SubCategorys subCategory) {
//		this.subCategory = subCategory;
//	}
//
// 


	public Set<Images> getImages() {
		return images;
	}


	public void setImages(Set<Images> images) {
		this.images = images;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public Long getCategory_Id() {
		return Category_Id;
	}


	public void setCategory_Id(Long category_Id) {
		Category_Id = category_Id;
	}

 
 
 
	

}
