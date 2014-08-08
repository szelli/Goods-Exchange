package com.szpzs.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Product;
import com.szpzs.model.ProductListDatas;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Product getProduct(long id) {
		try{
			Product product = entityManager.find(Product.class, id);
			return product;
		}catch(NoResultException ex){
			return null;
		}
		
	}

	public List<Product> getProductsByOwner(BigInteger ownerId){
		try {
			List<Product> product = entityManager.createQuery("Select p From Product as p Where p.ownerId = :ownerId")
				.setParameter("ownerId",ownerId).getResultList();
			return product;
		} catch(NoResultException ex) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public String saveProduct(Product product) {
		try{
			entityManager.persist(product);
			entityManager.flush();
			return "ok";
		}catch(Exception ex){
			return "product not saved";
		}
	}

	@Override
	public List<Product> getProductsList(ProductListDatas datas) {	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> ptable = cq.from(Product.class);
		ParameterExpression<BigInteger> p = cb.parameter(BigInteger.class);
		ParameterExpression<BigInteger> l = cb.parameter(BigInteger.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (datas.getOwnerId() != null && datas.getOwnerId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(ptable.get("ownerId"),p));
		}
		
		if (datas.getCategoryId() != null && datas.getCategoryId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(ptable.get("categoryId"),l));
		}
	
		try{
			cq.select(ptable).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			if (datas.getSort().equals("DESC")){
				cq.orderBy(cb.desc(ptable.get(datas.getTab())));
			} else {
				cq.orderBy(cb.asc(ptable.get(datas.getTab())));
			}
				
			TypedQuery<Product> query = entityManager.createQuery(cq);
			if (datas.getOwnerId() != null && datas.getOwnerId() != BigInteger.valueOf(0)){
				query.setParameter(p, datas.getOwnerId());
			}
			if (datas.getCategoryId() != null && datas.getCategoryId() != BigInteger.valueOf(0)){
				query.setParameter(l, datas.getCategoryId());
			}
			query.setFirstResult(datas.getOffset());
			query.setMaxResults(datas.getLimit());
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public int getProductsCount(Product datas) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> ptable = cq.from(Product.class);
		ParameterExpression<BigInteger> p = cb.parameter(BigInteger.class);
		ParameterExpression<BigInteger> l = cb.parameter(BigInteger.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (datas.getOwnerId() != null && datas.getOwnerId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(ptable.get("ownerId"),p));
		}
		
		if (datas.getCategoryId() != null && datas.getCategoryId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(ptable.get("categoryId"),l));
		}
	
		try{
			cq.select(ptable).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				
			TypedQuery<Product> query = entityManager.createQuery(cq);
			if (datas.getOwnerId() != null && datas.getOwnerId() != BigInteger.valueOf(0)){
				query.setParameter(p, datas.getOwnerId());
			}
			if (datas.getCategoryId() != null && datas.getCategoryId() != BigInteger.valueOf(0)){
				query.setParameter(l, datas.getCategoryId());
			}
		    
			return query.getResultList().size();
		} catch(NoResultException e) {
			return 0;
		}
	}
	
	@Override
	@Transactional
	public String updateProduct(Product product) {
		try{
			entityManager.merge(product);
			entityManager.flush();
			return "ok";
		}catch(NoResultException ex){
			return null;
		}
	} 

}
