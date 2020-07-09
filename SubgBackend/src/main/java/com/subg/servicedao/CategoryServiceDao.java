package com.subg.servicedao;

import java.util.List;
import java.util.Optional;

import com.subg.model.Categorys;

public interface CategoryServiceDao {

	Categorys addCategory(Categorys category);
	Categorys updateCategory(Categorys category);
	List<Categorys> getCategoryList();
	Optional<Categorys> getCategory(Long categoryId);
	void deleteCategory(Long categoryId);
	 
	
}
