package com.subg.servicedao;

import java.util.List;

import com.subg.model.Cart;

public interface CartServiceDao {

	Cart addCart(Cart cart);
	void deleteCartItem(Long cart_id);
	List<Cart> findByAllCartItems(long user_id);
	List<Cart> getAllCartItems();
	int countCartItems(Long user_id);
	
	
}
