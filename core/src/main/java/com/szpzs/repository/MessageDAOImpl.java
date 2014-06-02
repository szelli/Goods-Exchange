package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szpzs.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO{

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void SaveMessage(Message message){
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
	}


	@Override
	public Message GetMessage(long id) {
		Session session = sessionFactory.getCurrentSession();
		Message message = (Message) session.get(Message.class, id);
		return message;
	}
	
	@Override
	public List <Message> ListAllMessage(BigInteger personid) {
		Session session = sessionFactory.getCurrentSession();
		List message = session.createCriteria(Message.class).add( Restrictions.like("receiverId", personid) ).list();
		System.out.println(message);
		return message;
	}
}
