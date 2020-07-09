package com.subg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.subg.model.Cart;
import com.subg.model.Orders;

public interface OrdersDaoRepository extends JpaRepository<Orders, Long> {

	
	Orders findByTxnId(String txnId);
	
	@Query("from Orders  where user_id = :user_id")
	List<Cart> findByAllOrderItems(Long user_id);
	
//	@Modifying
//	@Query("update Orders o set o.Transaction_Id = :transactionId,o.Payment_Status = :payStatus where o.Order_Id = :orderId")
//	void updateOrder(@Param("transactionId") long transactionId,@Param("payStatus") long payStatus, @Param("orderId") Long orderId);
}
