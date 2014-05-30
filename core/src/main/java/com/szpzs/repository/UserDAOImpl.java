package com.szpzs.repository;

import java.util.Iterator;
import java.util.List;

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
		
	@Override
	public User getUserById(Long id){
		Session session = sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("Select u From User as u Where u.id = :id");
		query.setParameter("id",id);
		User user = (User) query.uniqueResult();*/
		User user = (User) session.get(User.class,id);
		return user;
	}
	
	@Override
	public Boolean existsUser(String userName){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select u From User as u Where u.userName = :username");
		query.setParameter("username",userName);
		User user = (User) query.uniqueResult();
		if(user == null){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void deleteUserById(Long id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete From User as u Where u.id = :id");
		query.setParameter("id",id);
		query.executeUpdate();
	}

	@Override
	public List<User> getAllUser() {
		Session session = sessionFactory.getCurrentSession();
		List userList = session.createQuery("Select u from User as u").list();
		return userList;
	}
	
	@Override
	public void updateUser(User newUser){
		Session session = sessionFactory.getCurrentSession();
		session.merge(newUser);
	}
}
