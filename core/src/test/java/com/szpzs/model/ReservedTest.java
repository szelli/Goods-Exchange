package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ReservedTest {
	
	Reserved reserved;
	
	@Before
	public void setUp(){
		reserved = new Reserved();
	}

	@Test
	public void testEmptyid(){
		assertNull( reserved.getId() );
	}
	
	@Test
	public void testEmptyName(){
		assertNull( reserved.getFromDate() );
	}
	
	@Test
	public void testEmptyArea(){
		assertNull( reserved.getToDate());
	}
	
	@Test
	public void testEmptyProductId(){
		assertNull( reserved.getProductId() );
	}
	
	@Test
	public void testEmptyReserverId(){
		assertNull( reserved.getReserverId() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		reserved.setId(testId);
		assertEquals( testId, reserved.getId() );
	}
	
	@Test
	public void testSetAndGetFromDate(){
		Date testBegin = new Date();
		reserved.setFromDate(testBegin);
		assertEquals( testBegin, reserved.getFromDate() );
	}
	
	@Test
	public void testSetAndGetToDate(){
		Date testEnd = new Date();
		reserved.setToDate(testEnd);
		assertEquals( testEnd, reserved.getToDate() );
	}
	
	@Test
	public void testSetAndGetProductId(){
		BigInteger testProductId = BigInteger.valueOf(1);
		reserved.setProductId(testProductId);
		assertEquals( testProductId, reserved.getProductId() );
	}
	
	@Test
	public void testSetAndGetReserverId(){
		BigInteger testReserverId = BigInteger.valueOf(1);
		reserved.setReserverId(testReserverId);
		assertEquals( testReserverId, reserved.getReserverId() );
	}

}
