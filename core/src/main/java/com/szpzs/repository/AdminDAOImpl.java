package com.szpzs.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Category;
import com.szpzs.model.User;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		List<User> users = entityManager.createQuery("SELECT u FROM User u Order by id Asc").getResultList();
		return users;
	}
	
}
