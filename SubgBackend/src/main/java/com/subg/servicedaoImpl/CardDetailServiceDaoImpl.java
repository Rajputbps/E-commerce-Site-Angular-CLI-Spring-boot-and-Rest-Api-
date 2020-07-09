package com.subg.servicedaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subg.dao.CardDetailDaoRepository;
import com.subg.model.CardDetail;
import com.subg.servicedao.CardDetailServiceDao;

@Service
@Transactional
public class CardDetailServiceDaoImpl implements CardDetailServiceDao{

	
	@Autowired
	private CardDetailDaoRepository cardDetailDaoRepository;
	
	@Override
	public CardDetail addCardDetail(CardDetail cardDetail) { 
		return cardDetailDaoRepository.save(cardDetail);
	}

}
