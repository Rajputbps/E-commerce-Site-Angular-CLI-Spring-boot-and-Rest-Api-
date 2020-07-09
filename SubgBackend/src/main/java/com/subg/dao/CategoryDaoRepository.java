package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subg.model.Categorys;

public interface CategoryDaoRepository extends JpaRepository<Categorys, Long>{


//	@Query("from Categorys where categoryId = :catid")
//	List<Categorys> getCategoryListById(long catid);

}
