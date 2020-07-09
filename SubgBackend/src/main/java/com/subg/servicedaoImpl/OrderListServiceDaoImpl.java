package com.subg.servicedaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subg.dao.OrderListDaoRepository;
import com.subg.model.OrderList;
import com.subg.servicedao.OrderListServiceDao;


@Service
@Transactional
public class OrderListServiceDaoImpl implements OrderListServiceDao {

	
	@Autowired
	private OrderListDaoRepository orderListDaoRepository;
	
	@Override
	public OrderList addorderList(OrderList orderList) { 
		return orderListDaoRepository.save(orderList);
	}

}
