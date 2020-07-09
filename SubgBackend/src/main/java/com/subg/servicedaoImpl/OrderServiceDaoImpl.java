package com.subg.servicedaoImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subg.dao.OrdersDaoRepository;
import com.subg.model.Cart;
import com.subg.model.Orders; 
import com.subg.payment.PaymentCallback;
import com.subg.payment.PaymentDetail;
import com.subg.payment.PaymentStatus;
import com.subg.payment.PaymentUtil;
import com.subg.servicedao.OrderServiceDao;

@Service
@Transactional
public class OrderServiceDaoImpl implements  OrderServiceDao{

	
	
	@Autowired
	private OrdersDaoRepository ordersDaoRepository;
	
	@Override
	public List<Orders> getAllOrder() { 
		return ordersDaoRepository.findAll();
	}

	@Override
	public Orders addOrder(Orders orders) { 
		return ordersDaoRepository.save(orders);
	}

	@Override
	public Orders updateOrder(Orders orders) { 
		return null;
	}

	@Override
	public List<Cart> findByAllOrderItems(Long user_id) { 
		return null;
	}

	@Override
	public void updateOrder(long transactionId, long payStatus, Long orderId) {
		 
		
	}

	@Override
	public void deleteOrder(long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaymentDetail proceedPayment(PaymentDetail paymentDetail) {
		 PaymentUtil paymentUtil = new PaymentUtil();
	        paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
	       // savePaymentDetail(paymentDetail);
	        return paymentDetail;
	}

	@Override
	public String payuCallback(PaymentCallback paymentResponse) {
		String msg = "Transaction failed.";
        Orders orders = ordersDaoRepository.findByTxnId(paymentResponse.getTxnid());
        if(orders != null) {
            //TODO validate the hash
            PaymentStatus paymentStatus = null;
            if(paymentResponse.getStatus().equals("failure")){
                paymentStatus = PaymentStatus.Failed;
            }else if(paymentResponse.getStatus().equals("success")) {
                paymentStatus = PaymentStatus.Success;
                msg = "Transaction success";
            }
            orders.setPaymentStatus(paymentStatus);
            orders.setMihpayId(paymentResponse.getMihpayid());
            orders.setMode(paymentResponse.getMode());
            ordersDaoRepository.save(orders);
        }
        return msg;
	}
	
	private void savePaymentDetail(PaymentDetail paymentDetail) {
        Orders order = new Orders();
        LocalTime localTime = LocalTime.now();
		LocalDate localDate = LocalDate.now();
        order.setOrder_Data(localDate);
        order.setOrder_Time(localTime);
        //order.setUser_Id(paymentDetail.getUser_id());
        order.setPaymentStatus(PaymentStatus.Pending); 
        order.setTxnId(paymentDetail.getTxnId());
        ordersDaoRepository.save(order);
    }

}
