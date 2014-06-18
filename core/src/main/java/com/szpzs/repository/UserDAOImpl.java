package com.szpzs.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.PersistentObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public User getUser(String userName, String password){
		try{
			User user = (User) entityManager.createQuery("Select u From User as u Where u.userName = :userName and u.password = :password")
			.setParameter("userName",userName)
			.setParameter("password", password)
			.getSingleResult();
			return user;
		} catch(NoResultException e) {
	        return null;
	    }
	}

	@Override
	@Transactional
	public void saveUser(User user){
		entityManager.persist(user);
		entityManager.flush();
	}

	@Override
	public boolean existsUser(String userName) {
		int size = entityManager.createQuery("Select u From User as u Where u.userName = :userName")
		.setParameter("userName",userName)
		.getResultList().size();
		if (size == 0){
			return false;
		} else {
			return true;
		}
	}

}
