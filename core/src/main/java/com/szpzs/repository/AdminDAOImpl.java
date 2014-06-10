package com.szpzs.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szpzs.model.Categories;

@Repository
public class AdminDAOImpl implements AdminDAO{

	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public List<Categories> getCategoryList() {
		Session session = sessionFactory.getCurrentSession();
		List categorys = session.createQuery("Select c from Categories as c").list();
		return categorys;
	}

	@Override
	public Categories getCategoryById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Categories category = (Categories) session.get(Categories.class, id);
		return category;
	}

	@Override
	public void saveCategory(Categories category) {
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
		
	}

	@Override
	public void updateCategory(Categories category) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(category);
		
	}

	@Override
	public void removeCategory(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query  query = session.createQuery("Delete from Categories as c where c.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
}