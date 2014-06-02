package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {
		
	Message message;
	
	@Before
	public void setUp(){
		message = new Message();
	}

	@Test
	public void testEmptyid(){
		assertNull(message.getId() );
	}
	
	@Test
	public void testEmptyMessage(){
		assertNull( message.getMessage() );
	}
	
	@Test
	public void testEmptyRead(){
		assertNull( message.getRead() );
	}
	
	@Test
	public void testEmptyReceiverId(){
		assertNull( message.getReceiverId() );
	}
	
	@Test
	public void testEmptyReceiverStatus(){
		assertNull( message.getReceiverStatus() );
	}
	
	@Test
	public void testEmptySendDate(){
		assertNull( message.getSendDate() );
	}
	
	@Test
	public void testEmptySenderId(){
		assertNull( message.getSenderId());
	}
	
	@Test
	public void testEmptySenderStatus(){
		assertNull(message.getSenderStatus());
	}
	
	@Test
	public void testEmptySubject(){
		assertNull(message.getSubject());
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		message.setId(testId);
		assertEquals( testId, message.getId() );
	}
	
	@Test
	public void testSetAndGetMessage(){
		String testMessage = "Ez egy üzenet";
		message. setMessage(testMessage);
		assertEquals( testMessage, message.getMessage() );
	}	
	
	@Test
	public void testSetAndGetRead(){
		BigInteger testRead = BigInteger.valueOf(1);
		message.setRead(testRead);
		assertEquals( testRead, message.getRead() );
	}
	
	@Test
	public void testSetAndGetReceiverId(){
		BigInteger testReceiverId = BigInteger.valueOf(1);
		message.setReceiverId(testReceiverId);
		assertEquals( testReceiverId, message.getReceiverId() );
	}
	
	@Test
	public void testSetAndGetReceiverStatus(){
		BigInteger testReceiverStatus = BigInteger.valueOf(1);
		message.setReceiverStatus(testReceiverStatus);
		assertEquals( testReceiverStatus, message.getReceiverStatus() );
	}
	
	@Test
	public void testSetAndGetSendDate(){
		Date testSendDate = new Date();
		message.setSendDate(testSendDate);
		assertEquals( testSendDate, message.getSendDate() );
	}
	
	@Test
	public void testSetAndGetSenderId(){
		BigInteger testSenderId = BigInteger.valueOf(1);
		message.setSenderId(testSenderId);
		assertEquals( testSenderId, message.getSenderId() );
	}
	
	@Test
	public void testSetAndGetSenderStatus(){
		BigInteger testSenderStatus = BigInteger.valueOf(1);
		message.setSenderStatus(testSenderStatus);
		assertEquals( testSenderStatus, message.getSenderStatus() );
	}
	
	@Test
	public void testSetAndGetSubject(){
		String testSubject = "Ez egy tárgy";
		message.setSubject(testSubject);
		assertEquals( testSubject, message.getSubject() );
	}

}
