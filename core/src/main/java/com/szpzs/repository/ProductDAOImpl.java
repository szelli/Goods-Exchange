package com.szpzs.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Product getProduct(long id) {
		Product product = entityManager.find(Product.class, id);
		return product;
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		entityManager.persist(product);
		entityManager.flush();
	}

	@Override
	public boolean existsProduct(Product product) {
		int size = entityManager.createQuery("Select p From Product as p Where p.name = :name AND p.ownerId = :ownerId AND p.descriptions = :disc AND p.categoryId = :categoryId")
		.setParameter("name", product.getName())
		.setParameter("ownerId", product.getOwnerId())
		.setParameter("disc", product.getDescriptions())
		.setParameter("categoryId", product.getCategoryId())
		.getResultList().size();
		if (size == 0){
			return false;
		} else {
			return true;
		}

	}

}
