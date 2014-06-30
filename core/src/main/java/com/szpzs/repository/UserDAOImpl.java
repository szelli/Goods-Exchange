package com.szpzs.repository;

import java.math.BigInteger;

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
	public User getUserById(Long id){
		try{
			User user = (User) entityManager.createQuery("Select u From User as u Where u.id = :id")
				.setParameter("id",id)
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
	@Transactional
	//még át kell írni a Criretia API használatával
	public void editUser(User user) {
		entityManager.createQuery("Update User as u Set u.postcode = :postcode, u.city = :city, u.address = :address, u.email = :email Where u.id = :id")
		.setParameter("postcode", user.getPostcode())
		.setParameter("city", user.getCity())
		.setParameter("address", user.getAddress())
		.setParameter("email", user.getEmail())
		.setParameter("id", user.getId()).executeUpdate();
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
