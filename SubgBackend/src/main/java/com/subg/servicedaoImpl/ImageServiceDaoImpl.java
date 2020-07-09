package com.subg.servicedaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subg.dao.ImageDaoRepository;
import com.subg.model.Images;
import com.subg.servicedao.ImageServiceDao;

@Service
public class ImageServiceDaoImpl implements ImageServiceDao {

	@Autowired
	private ImageDaoRepository imageDaoRepository;
	
	@Override
	public Images addImages(Images images) {
		return imageDaoRepository.save(images);
	}

	@Override
	public List<Images> getProductImage(long product_id) { 
		return imageDaoRepository.getProductImage(product_id);
	}

}
