package com.subg.servicedaoImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.subg.dao.SubCategoryDaoRepository;
import com.subg.model.SubCategorys;
import com.subg.servicedao.SubCategoryServiceDao;


@Service
@Transactional
@Cacheable("subCategory")
public class SubCategoryServiceDaoImpl implements SubCategoryServiceDao{
	
	@Autowired
	private SubCategoryDaoRepository categorSubCategoryDaoRepository;

	public SubCategoryServiceDaoImpl() { 	}

	@Override
	public SubCategorys addSubCategory(SubCategorys subCategory) { 
		return categorSubCategoryDaoRepository.save(subCategory);
	}

	@Override
	public SubCategorys updateSubCategory(SubCategorys subCategory) { 
		return categorSubCategoryDaoRepository.saveAndFlush(subCategory);
	}

	@Override
	public List<SubCategorys> getSubCategoryList() { 
		return categorSubCategoryDaoRepository.findAll();
	}

	@Override
	public Optional<SubCategorys> getSubCategory(Long subcategoryId) { 
		return categorSubCategoryDaoRepository.findById(subcategoryId);
	}

	@Override
	public void deleteSubCategory(Long subcategoryId) { 
		categorSubCategoryDaoRepository.deleteById(subcategoryId);
	}

	@Override
	public SubCategorys getSubCategoryProduct(long sub_id) { 
		return categorSubCategoryDaoRepository.getSubCategoryProduct(sub_id);
	}

}
