package com.subg.servicedao;

import java.util.List;

import com.subg.model.Rating;

public interface RatingServiceDao {

	Rating addRating(Rating rating);
	List<Rating> getAllRating();
	public double getRating();
	public double getProductRating(long product_id);
	
	
}
