package com.subg.servicedaoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.subg.dao.ProductDaoRepository; 
import com.subg.model.Products; 
import com.subg.servicedao.ProductServiceDao;

@Service
@Transactional
@Cacheable("Products")
public class ProductServiceDaoImpl implements ProductServiceDao{

	@Autowired
	private ProductDaoRepository productDaoRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public ProductServiceDaoImpl(EntityManager centityManager ) {
		super();
		this.entityManager = centityManager;
		//initializeHibernateSearch();
	}

	@Override
	public Products addProduct(Products product) { 
		return productDaoRepository.save(product);
	}

	@Override
	public Products updateProduct(Products product) { 
		return productDaoRepository.saveAndFlush(product);
	}

	@Override
	public List<Products> getProductList() { 
		return productDaoRepository.findAll();
	}

	@Override
	public Optional<Products> getProduct(Long productId) { 
		return productDaoRepository.findById(productId);
	}

	@Override
	public void deleteProduct(Long productId) { 
		productDaoRepository.deleteById(productId);
	}

	@Override
	public List<Products> getSubndProList(Long subcategoryid) { 
		return productDaoRepository.findBySubcategory(subcategoryid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> findByName(String name) { 
		FullTextEntityManager  fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Products.class).get();
		Query query = queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("product_name","categoryName").matching(name).createQuery();
		FullTextQuery  fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Products.class);
				return fullTextQuery.getResultList();
	}

	@Override
	public Products getProductFullDetail(Long product_id) { 
		return productDaoRepository.getProductFullDetail(product_id);
	}

	@Override
	public void initializeHibernateSearch() {
		 try {
			
			 FullTextEntityManager  fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			 fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	 

}
