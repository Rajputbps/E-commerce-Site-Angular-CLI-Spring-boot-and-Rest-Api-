package com.subg.servicedao;

import java.util.List;
import java.util.Optional;

import com.subg.model.Products;

public interface ProductServiceDao {

	Products addProduct(Products product);
	Products updateProduct(Products product);
	List<Products> getProductList();
	List<Products> getSubndProList(Long subcategoryid);
	Optional<Products> getProduct(Long productId);
	void deleteProduct(Long productId);
	//Search
	List<Products> findByName(String name);
	Products getProductFullDetail(Long product_id);
	public void initializeHibernateSearch();
	
}
