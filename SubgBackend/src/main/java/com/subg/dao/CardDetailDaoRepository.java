package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subg.model.CardDetail;

public interface CardDetailDaoRepository extends JpaRepository<CardDetail, Long> {

}
