package com.subg.servicedaoImpl;

import java.util.List;
import java.util.Optional;

 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.subg.dao.CategoryDaoRepository;
import com.subg.model.Categorys;
import com.subg.servicedao.CategoryServiceDao;


@Service
@Transactional
@Cacheable("Categorys")
public class CategoryServiceDaoImpl implements CategoryServiceDao{

	@Autowired
	private CategoryDaoRepository categoryDaoRepository;
	
	
	public CategoryServiceDaoImpl() {
		 
		
	}

	@Override
	public Categorys addCategory(Categorys category) { 
		return categoryDaoRepository.save(category);
	}

	@Override
	public Categorys updateCategory(Categorys category) { 
		return categoryDaoRepository.saveAndFlush(category);
	}

	@Override
	public List<Categorys> getCategoryList() { 
		return categoryDaoRepository.findAll();
	}

	@Override
	public Optional<Categorys> getCategory(Long categoryId) { 
		return categoryDaoRepository.findById(categoryId);
	}

	@Override
	public void deleteCategory(Long categoryId) { 
		categoryDaoRepository.deleteById(categoryId);
	}

 

}
