package com.szpzs.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Message> getMessages(Message datas) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> mtable = cq.from(Message.class);
		ParameterExpression<BigInteger> receiverId = cb.parameter(BigInteger.class);
		ParameterExpression<Boolean> receiverStatus = cb.parameter(Boolean.class);
		ParameterExpression<BigInteger> senderId = cb.parameter(BigInteger.class);
		ParameterExpression<Boolean> senderStatus = cb.parameter(Boolean.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (datas.getReceiverId() != null && datas.getReceiverId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(mtable.get("receiverId"),receiverId));
			predicates.add(cb.equal(mtable.get("receiverStatus"),receiverStatus));
			
		}
		if (datas.getSenderId() != null && datas.getSenderId() != BigInteger.valueOf(0)){
			predicates.add(cb.equal(mtable.get("senderId"),senderId));
			predicates.add(cb.equal(mtable.get("senderStatus"),senderStatus));
		}
		try{
			cq.select(mtable).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Message> query = entityManager.createQuery(cq);
			if (datas.getReceiverId() != null && datas.getReceiverId() != BigInteger.valueOf(0)){
				query.setParameter(receiverId, datas.getReceiverId());
				query.setParameter(receiverStatus, true);
			}
			if (datas.getSenderId() != null && datas.getSenderId() != BigInteger.valueOf(0)){
				query.setParameter(senderId, datas.getSenderId());
				query.setParameter(senderStatus, true);
			}
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public String saveMessage(Message message) {
		entityManager.persist(message);
		entityManager.flush();
		return "ok";
	}

	@Override
	@Transactional
	public String changeStatus(Message message) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Message> cu = cb. createCriteriaUpdate(Message.class);
		Root<Message> mtable = cu.from(Message.class);
		
		try{
			cu.where(cb.equal(mtable.get("id"), message.getId()));
			
			if (message.getRead() != null){
				cu.set("read", message.getRead());
			}
			if (message.getReceiverStatus() != null){
				cu.set("receiverStatus", message.getReceiverStatus());
			}
			if (message.getSenderStatus() != null){
				cu.set("senderStatus", message.getSenderStatus());
			}
			
			entityManager.createQuery(cu).executeUpdate();
			return "ok";
		} catch(NoResultException e) {
			return null;
		}
	}

	/*@Override
	@Transactional
	public String deleteMessage(Long id) {
		try {
			entityManager.createQuery("Delete From Message as m Where m.id = :id").setParameter("id", id).executeUpdate();
		} catch(NoResultException e) {
	        return "not ok";
	    }
		return "ok";
	}*/
}
