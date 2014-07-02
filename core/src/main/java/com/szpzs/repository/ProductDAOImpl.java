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
		Product product = entityManager.find(Product.class, id);
		return product;
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		entityManager.persist(product);
		entityManager.flush();
	}

	@Override
	public boolean existsProduct(Product product) {
		int size = entityManager.createQuery("Select p From Product as p Where p.name = :name AND p.ownerId = :ownerId AND p.descriptions = :disc AND p.categoryId = :categoryId")
		.setParameter("name", product.getName())
		.setParameter("ownerId", product.getOwnerId())
		.setParameter("disc", product.getDescriptions())
		.setParameter("categoryId", product.getCategoryId())
		.getResultList().size();
		if (size == 0){
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<Product> getProductsList(ProductListDatas datas) {
		int product_count;
		
		if (datas.getId() == null){
			product_count = this.getProductsCount();
		} else {
			product_count = this.getProductsCountByOwner(datas.getId());
		}
		
		if (product_count == 0){
			return null;
		}
		
		int page_num = (int) Math.ceil((double)product_count/(double)datas.getLimit());
		if(datas.getPage()>page_num || datas.getPage() == 0){
			datas.setPage(page_num);
		}
		int offset = (datas.getPage()-1)*datas.getLimit();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> q = cb.createQuery(Product.class);
		Root<Product> c = q.from(Product.class);
		ParameterExpression<BigInteger> p = cb.parameter(BigInteger.class);
		//List<Predicate> predicates = new ArrayList<Predicate>();
		//predicates.add(cb.equal(c.get("ownerId"),p));
		try{
			q.select(c).where(cb.equal(c.get("ownerId"),p));
			//q.select(c).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			//q.select(c).where(cb.or(c.get("name").isNotNull(), cb.equal(c.get("ownerId"),p)));
			TypedQuery<Product> query = entityManager.createQuery(q);
			query.setParameter(p, datas.getId());
			query.setFirstResult(offset);
			query.setMaxResults(datas.getLimit());
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public int getProductsCountByOwner(BigInteger id) {
			Query q = entityManager.createQuery("Select count(q.id) as count From Product as q Where q.ownerId = :ownerId")
					.setParameter("ownerId",id);
			return ((Long) q.getSingleResult()).intValue();
	}

	@Override
	public int getProductsCount() {
			Query q = entityManager.createQuery("Select count(q.id) as count From Product as q");
			return ((Long) q.getSingleResult()).intValue();
	}

}
