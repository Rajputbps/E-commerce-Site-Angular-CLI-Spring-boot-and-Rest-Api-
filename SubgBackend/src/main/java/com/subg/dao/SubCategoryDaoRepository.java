package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subg.model.SubCategorys;

public interface SubCategoryDaoRepository extends JpaRepository<SubCategorys, Long> {

	@Query("from SubCategorys where subcategoryId = :sub_id")
	SubCategorys getSubCategoryProduct(long sub_id);
	
}
