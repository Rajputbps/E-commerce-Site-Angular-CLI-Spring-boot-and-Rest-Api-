package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subg.model.Rating;

public interface RatingDaoRepository extends JpaRepository<Rating, Long>{
	
	@Query("SELECT AVG(rating) FROM Rating") 
	public double getRating();
	
	@Query("SELECT AVG(rating) FROM Rating where product_id = :product_id")
	public double getProductRating(long product_id);

}
