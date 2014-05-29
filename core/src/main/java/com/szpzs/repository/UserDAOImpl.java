package com.szpzs.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szpzs.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user){
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
