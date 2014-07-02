package com.szpzs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Categories;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Categories> ListAllCategory() {
		List<Categories> categories = entityManager.createQuery("SELECT c FROM Categories c").getResultList();
		return categories;
	}

	@Override
	@Transactional
	public String SaveCategory(Categories category) {
		entityManager.persist(category);
		entityManager.flush();
		return "OK";
	}

}
