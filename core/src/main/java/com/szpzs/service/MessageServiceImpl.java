package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Message;
import com.szpzs.repository.MessageDAO;

@Service("MessageServiceImpl")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDAO messageDAO;

	@Override
	@Transactional
	public void SaveMessage(Message message) {
		messageDAO.SaveMessage(message);
		
	}
	@Override
	@Transactional
	public void SenderDeleteMsg(long id){
		Message message = messageDAO.GetMessage(id);
		message.setSenderStatus(BigInteger.valueOf(0));
	}

	@Override
	@Transactional
	public void ReceiverDeleteMsg(long id) {
		Message message = messageDAO.GetMessage(id);
		message.setReceiverStatus(BigInteger.valueOf(0));
	}

	@Override
	@Transactional
	public void SetAsRead(long id) {
		Message message = messageDAO.GetMessage(id);
		message.setRead(BigInteger.valueOf(1));
	}


	@Override
	@Transactional
	public void ListAllMessage(BigInteger personid) {
		List listAll = messageDAO.ListAllMessage(personid);
	}
}
