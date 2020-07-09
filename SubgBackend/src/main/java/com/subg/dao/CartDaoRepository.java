package com.subg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subg.model.Cart;

public interface CartDaoRepository extends JpaRepository<Cart, Long>{

	@Query("from Cart  where user_id = :user_id")
	List<Cart> findByAllCartItems(Long user_id);
	

	@Query("select count(*) from Cart  where user_id = :user_id")
	int countCartItems(Long user_id);
 
	
}
