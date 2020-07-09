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
public class Categorys {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryId;
	private String categoryName;
	private String categoryDesc;
	
	
	@OneToMany(mappedBy="categoryId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<SubCategorys> subCategories;
	
	public Categorys() {
		 
	}

	 

	 

	public long getCategoryId() {
		return categoryId;
	}





	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}





	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Set<SubCategorys> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategorys> subCategories) {
		this.subCategories = subCategories;
	}

	
}
