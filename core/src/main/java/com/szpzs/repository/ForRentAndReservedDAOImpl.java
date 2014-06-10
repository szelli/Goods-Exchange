package com.szpzs.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;

@Repository
public class ForRentAndReservedDAOImpl implements ForRentAndReservedDAO{
	@Autowired private SessionFactory sessionFactory;

	@Override
	public void saveForRent(ForRent forRent){
		Session session = sessionFactory.getCurrentSession();
		session.save(forRent);
	}
	
	/*@Override
	public List<ForRent> getForRentsByUser(Long id){
		Session session = sessionFactory.getCurrentSession();
		return null;
	}*/

	/*@Override
	public Boolean existsForRent(BigInteger productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select fr From ForRent as fr Where fr.productId=:pId");
		query.setParameter("pId", productId);
		ForRent forRent = (ForRent) query.uniqueResult();
		if(forRent == null){
			return false;
		}else{
			return true;
		}
	}*/

	/*@Override
	public void updateForRent(ForRent forRent) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(forRent);
	}*/

	@Override
	public void deleteForRent(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete From ForRent as fr Where fr.id = :id");
		query.setParameter("id",id);
		query.executeUpdate();
	}
	
	@Override
	public List<ForRent> getForRentsByProductId(BigInteger productId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select fr From ForRent as fr Where fr.productId=:pId");
		query.setParameter("pId", productId);
		List<ForRent> forRents = query.list();
		return forRents;
	}

	@Override
	public void saveReserved(Reserved reserved) {
		Session session = sessionFactory.getCurrentSession();
		session.save(reserved);
	}
	
	@Override
	public ForRent getForRentByDateInterval(BigInteger productId, Date begin, Date end) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select fr From ForRent as fr Where fr.productId = :pId and fr.fromDate <= :fromDate and fr.toDate >= :toDate");
		query.setParameter("pId", productId);
		query.setParameter("fromDate", begin);
		query.setParameter("toDate", end);
		
		ForRent fr= (ForRent) query.uniqueResult();
		return fr;
	}

	@Override
	public List<ForRent> getReservedByProductId(BigInteger productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r From Reserved as r Where r.productId=:pId");
		query.setParameter("pId", productId);
		List<ForRent> reserved = query.list();
		return reserved;
	}	
}
