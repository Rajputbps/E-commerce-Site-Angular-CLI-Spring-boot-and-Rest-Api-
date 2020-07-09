package com.subg.servicedao;

import java.util.List;
import java.util.Optional;

import com.subg.model.SubCategorys;

public interface SubCategoryServiceDao {

	SubCategorys addSubCategory(SubCategorys subCategory);
	SubCategorys updateSubCategory(SubCategorys subCategory);
	List<SubCategorys> getSubCategoryList();
	Optional<SubCategorys> getSubCategory(Long subcategoryId);
	void deleteSubCategory(Long subcategoryId);
	SubCategorys getSubCategoryProduct(long sub_id);
	
}
