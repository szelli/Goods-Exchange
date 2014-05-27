package com.szpzs.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szpzs.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query  query = session.createQuery("Select p from Product as p where p.id = :id");
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	@Override
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
	}

	
}
