package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subg.model.OrderList;

public interface OrderListDaoRepository extends JpaRepository<OrderList, Long>{

}
