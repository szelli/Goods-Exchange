package com.szpzs.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.PersistentObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.ForRent;
//import com.szpzs.model.Reserved;
import com.szpzs.model.User;

@Repository
public class ForRentAndReservedDAOImpl implements ForRentAndReservedDAO {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public void saveForRent(ForRent forRent) {
		entityManager.persist(forRent);
		entityManager.flush();
	}
	
	@Override
	@Transactional
	public List<ForRent> getForRentsByProductId(BigInteger productId){
		try{
			Query q = entityManager.createQuery("Select fr From ForRent as fr Where fr.productId=:pId")
					  .setParameter("pId", productId);
			List<ForRent> forRents = q.getResultList();
			return forRents;
		}catch(NoResultException e){
			return null;
		}
	}
}
