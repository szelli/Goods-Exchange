package com.szpzs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Category> ListAllCategory() {
		List<Category> categories = entityManager.createQuery("SELECT c FROM Category c").getResultList();
		return categories;
	}

	@Override
	@Transactional
	public String SaveCategory(Category category) {
		entityManager.persist(category);
		entityManager.flush();
		return "OK";
	}

}
