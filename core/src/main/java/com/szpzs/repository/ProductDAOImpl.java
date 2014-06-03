package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProductList() {
		Session session = sessionFactory.getCurrentSession();
		List products = session.createQuery("Select p from Product as p").list();
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
	}
	
	@Override
	public Product getProduct(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	}

	@Override
	public void update(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(product);
	}



}
