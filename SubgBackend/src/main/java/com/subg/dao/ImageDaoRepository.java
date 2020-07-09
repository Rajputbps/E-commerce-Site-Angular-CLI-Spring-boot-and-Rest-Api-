package com.subg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subg.model.Images;

public interface ImageDaoRepository  extends JpaRepository<Images, Long>{
	
	@Query("from Images where product_id = :product_id")
	List<Images> getProductImage(long product_id);
	
}
