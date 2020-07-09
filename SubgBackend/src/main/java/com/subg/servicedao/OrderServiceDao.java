package com.subg.servicedao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.subg.model.Cart;
import com.subg.model.Orders;
import com.subg.payment.PaymentCallback;
import com.subg.payment.PaymentDetail;

public interface OrderServiceDao {

	Orders addOrder(Orders orders);
	Orders updateOrder(Orders orders);
	List<Orders> getAllOrder();
	List<Cart> findByAllOrderItems(Long user_id);
	void updateOrder(@Param("transactionId") long transactionId,@Param("payStatus") long payStatus, @Param("orderId") Long orderId);
	void deleteOrder(long orderId);
	

	public PaymentDetail proceedPayment(PaymentDetail paymentDetail) ;
	public String payuCallback(PaymentCallback paymentResponse) ;
}
