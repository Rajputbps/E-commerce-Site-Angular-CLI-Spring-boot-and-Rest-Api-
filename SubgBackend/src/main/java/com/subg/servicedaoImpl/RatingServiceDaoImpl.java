package com.subg.servicedaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subg.dao.RatingDaoRepository;
import com.subg.model.Rating;
import com.subg.servicedao.RatingServiceDao;

@Service
@Transactional
public class RatingServiceDaoImpl implements RatingServiceDao{
	
	@Autowired
	private RatingDaoRepository ratingDaoRepository;

	@Override
	public Rating addRating(Rating rating) {
		return ratingDaoRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		// TODO Auto-generated method stub
		return ratingDaoRepository.findAll();
	}

	@Override
	public double getRating() {
		 
		return ratingDaoRepository.getRating();
	}

	@Override
	public double getProductRating(long product_id) { 
		return ratingDaoRepository.getProductRating(product_id);
	}

}
