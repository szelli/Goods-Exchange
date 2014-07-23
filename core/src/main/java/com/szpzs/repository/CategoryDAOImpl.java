package com.szpzs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	@Override
	@Transactional
	public List<Category> getCategories() {
		List<Category> categories = entityManager.createQuery("SELECT c FROM Category c Order By parentId Asc").getResultList();		
	    return categories;
	  }
	
	@Override
	@Transactional
	public String saveCategory(Category category) {
		try{
			String name = category.getName();
			if(!existCategory(name)){
				entityManager.persist(category);
				entityManager.flush();
			} else return "already exist";
		} catch(NoResultException e) {
	        return "not ok";
	    }
		return "ok";
	}
	
	@Override
	@Transactional
	public String editCategory(Category category){
		try {
			entityManager.createQuery("Update Category as c Set c.name = :name, c.parentId = :parentId Where c.id = :id")
			.setParameter("id", category.getId())
			.setParameter("name", category.getName())
			.setParameter("parentId", category.getParentId()).executeUpdate();
		} catch(NoResultException e) {
	        return "not ok";
	    }
		return "ok";
	}
	
	@Override
	@Transactional
	public String deleteCategory(Long id){
		try {
			entityManager.createQuery("Delete From Category as c Where c.id = :id").setParameter("id", id).executeUpdate();
		} catch(NoResultException e) {
	        return "not ok";
	    }
		return "ok";
	}
	
	@Override
	@Transactional
	public boolean existCategory(String name){
		int size = entityManager.createQuery("SELECT c FROM Category as c Where c.name = :name")
			.setParameter("name",name)
			.getResultList().size();
			if (size == 0){
				return false;
			} else {
				return true;
			}
	}
	
}
