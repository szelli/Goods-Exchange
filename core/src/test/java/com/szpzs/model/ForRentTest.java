package com.szpzs.model;

import static org.junit.Assert.assertEquals;
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

	@Test
	public void testEmptyid(){
		assertNull( forRent.getId() );
	}
	
	@Test
	public void testEmptyName(){
		assertNull( forRent.getBegin() );
	}
	
	@Test
	public void testEmptyArea(){
		assertNull( forRent.getEnd());
	}
	
	@Test
	public void testEmptyProductId(){
		assertNull( forRent.getProductId() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		forRent.setId(testId);
		assertEquals( testId, forRent.getId() );
	}
	
	@Test
	public void testSetAndGetBegin(){
		Date testBegin = new Date();
		forRent.setBegin(testBegin);
		assertEquals( testBegin, forRent.getBegin() );
	}
	
	@Test
	public void testSetAndGetEnd(){
		Date testEnd = new Date();
		forRent.setEnd(testEnd);
		assertEquals( testEnd, forRent.getEnd() );
	}
	
	@Test
	public void testSetAndGetProductId(){
		BigInteger testProductId = BigInteger.valueOf(1);
		forRent.setProductId(testProductId);
		assertEquals( testProductId, forRent.getProductId() );
	}
	
}
