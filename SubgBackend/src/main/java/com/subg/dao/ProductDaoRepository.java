package com.subg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.subg.model.Products;

public interface ProductDaoRepository extends JpaRepository<Products, Long>{

	@Query(" from Products  where subcategory_id = :subcategory_id")
	List<Products> findBySubcategory( Long subcategory_id);
	
	@Query(" from Products where  product_name LIKE %:name% ")
	List<Products> findByName(String name);
	
	@Query(" from Products where productId = :product_id")
	Products getProductFullDetail(Long product_id);
}
