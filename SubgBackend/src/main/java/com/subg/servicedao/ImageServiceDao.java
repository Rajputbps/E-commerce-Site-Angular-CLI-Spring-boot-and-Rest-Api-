package com.subg.servicedao;

 
import java.util.List; 

import com.subg.model.Images;

public interface ImageServiceDao {

	public Images addImages(Images images);
	List<Images> getProductImage(long product_id);
}
