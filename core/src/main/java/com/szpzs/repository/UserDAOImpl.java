package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = entityManager.createQuery("SELECT u FROM User u Order by id Asc").getResultList();
		return users;
	}
	
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
	public User getUserByName(String userName) {
		try{
			User user = (User) entityManager.createQuery("Select u From User as u Where u.userName = :userName")
				.setParameter("userName",userName)
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
	public String editUser(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaUpdate<User> cu = cb. createCriteriaUpdate(User.class);
		Root<User> utable = cu.from(User.class);
		
		try{
			cu.where(cb.equal(utable.get("id"), user.getId()));
			
			if (user.getUserName() != null && user.getUserName() != String.valueOf(0)){
				cu.set("userName", user.getUserName());
			}
			if (user.getFullName() != null && user.getFullName() != String.valueOf(0)){
				cu.set("fullName", user.getFullName());
			}
			if (user.getPostcode() != null && user.getPostcode() != BigInteger.valueOf(0)){
				cu.set("postcode", user.getPostcode());
			}
			if (user.getCityId() != null && user.getCityId() != Long.valueOf(0)){
				cu.set("city", user.getCityId());
			}
			if (user.getAddress() != null && user.getAddress() != String.valueOf(0)){
				cu.set("address", user.getAddress());
			}
			if (user.getEmail() != null && user.getEmail() != String.valueOf(0)){
				cu.set("email", user.getEmail());
			}
			if (user.getPassword() != null && user.getPassword() != String.valueOf(0)){
				user.setPassword(convertPasswordToMd5(user.getPassword()));
				cu.set("password", user.getPassword());
			}
			if (user.getStatus() != null && user.getStatus() != BigInteger.valueOf(0)){
				cu.set("status", user.getStatus());
			}
			if (user.getRole().getId() != 0){
				cu.set("role", user.getRole().getId());
			}
			
			entityManager.createQuery(cu).executeUpdate();
			return "ok";
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean validatePassword(Long id, String password){
		try{
			int size = entityManager.createQuery("Select u From User as u Where u.id = :id and u.password = :password")
			.setParameter("id",id)
			.setParameter("password", password)
			.getResultList().size();
			if(size == 1){
				return true;
			} else {
				return false;
			}
		} catch(NoResultException e) {
	        return false;
	    }
	}
	
	@Override
	@Transactional
	public String deleteUser(Long id){
		try {
			entityManager.createQuery("Delete From User as u Where u.id = :id").setParameter("id", id).executeUpdate();
			return "ok";
		} catch(NoResultException e) {
	        return "not ok";
	    }
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
	
	@Override
	public String convertPasswordToMd5(String pass) {
		return new Md5PasswordEncoder().encodePassword( pass, null );
	}



	
}
