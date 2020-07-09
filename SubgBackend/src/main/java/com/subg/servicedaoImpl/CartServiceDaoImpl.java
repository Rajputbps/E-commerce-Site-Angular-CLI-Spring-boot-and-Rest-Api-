package com.subg.servicedaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.subg.dao.CartDaoRepository;
import com.subg.model.Cart;
import com.subg.servicedao.CartServiceDao;

@Service
@Transactional 
public class CartServiceDaoImpl implements CartServiceDao {
	
	@Autowired
	private CartDaoRepository cartDaoRepository;

	public CartServiceDaoImpl() {
		 
	}

	@Override
	public Cart addCart(Cart cart) {
		 
		return cartDaoRepository.save(cart);
	}

	 

	@Override
	public List<Cart> findByAllCartItems(long user_id) {
		 
		return cartDaoRepository.findByAllCartItems(user_id);
	}

	@Override
	public List<Cart> getAllCartItems() {
		 
		return cartDaoRepository.findAll();
	}

	@Override
	public void deleteCartItem(Long cart_id) {
		 
		cartDaoRepository.deleteById(cart_id);
	}

	@Override
	public int countCartItems(Long user_id) { 
		return cartDaoRepository.countCartItems(user_id);
	}

	 

 
		
 

	 

}
