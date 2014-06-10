package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ForRentTest {
	
	ForRent forRent;
	
	@Before
	public void setUp(){
		forRent = new ForRent();
	}

	/*@Test
	public void testEmptyId(){
		assertNull( forRent.getId());
	}*/
	
	@Test
	public void testEmptyFromDate(){
		assertNull( forRent.getFromDate() );
	}
	
	@Test
	public void testEmptyToDate(){
		assertNull( forRent.getToDate());
	}
	
	@Test
	public void testEmptyProductId(){
		assertNull( forRent.getProductId() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		forRent.setId(testId);
		assertEquals((long) testId, forRent.getId() );
	}
	
	@Test
	public void testSetAndGetFromDate(){
		Date testBegin = new Date();
		forRent.setFromDate(testBegin);
		assertEquals( testBegin, forRent.getFromDate() );
	}
	
	@Test
	public void testSetAndGetToDate(){
		Date testEnd = new Date();
		forRent.setToDate(testEnd);
		assertEquals( testEnd, forRent.getToDate() );
	}
	
	@Test
	public void testSetAndGetProductId(){
		BigInteger testProductId = BigInteger.valueOf(1);
		forRent.setProductId(testProductId);
		assertEquals( testProductId, forRent.getProductId() );
	}	
}
