package com.szpzs.repository;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageDAOImplTest {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Test
	@Transactional
	public void SaveMessageTest(){
		Message message = new Message();
		message.setId((long) 3);
		message.setMessage("üzenet");
		message.setRead(BigInteger.valueOf(0));
		message.setReceiverId(BigInteger.valueOf(11));
		message.setReceiverStatus(BigInteger.valueOf(1));
		message.setSendDate(new Date());
		message.setSenderId(BigInteger.valueOf(33));
		message.setSenderStatus(BigInteger.valueOf(0));
		message.setSubject("tárgy");
		
		messageDAO.SaveMessage(message);
		
		List<Message> messages = messageDAO.ListAllMessage(BigInteger.valueOf(11));
		Message message2 = messages.get(messages.size()-1);
		assertEquals(message.getId(), message2.getId());
		assertEquals(message.getMessage(), message2.getMessage());
		assertEquals(message.getRead(), message2.getRead());
		assertEquals(message.getReceiverId(), message2.getReceiverId());
		assertEquals(message.getReceiverStatus(), message2.getReceiverStatus());
		assertEquals(message.getSendDate(), message2.getSendDate());
		assertEquals(message.getSenderId(), message2.getSenderId());
		assertEquals(message.getSenderStatus(), message2.getSenderStatus());
		assertEquals(message.getSubject(), message2.getSubject());
		
		
	}
	
	@Test
	@Transactional
	public void GetMessageTest(){
		assertEquals(1, (long)messageDAO.GetMessage(1).getId());
	}
	
	@Test
	@Transactional
	public void ListAllMessagesTest(){
		assertEquals(0, messageDAO.ListAllMessage(BigInteger.valueOf(66666)).size());
	}
}
