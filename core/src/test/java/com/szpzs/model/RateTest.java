package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class RateTest {

	Rate rate;
	
	@Before
	public void setUp(){
		rate = new Rate();
	}

	@Test
	public void testEmptyid(){
		assertNull( rate.getId() );
	}
	
	@Test
	public void testEmptyPositive(){
		assertNull( rate.getPositive() );
	}
	
	@Test
	public void testEmptyProductId(){
		assertNull( rate.getProductId() );
	}
	
	@Test
	public void testEmptyRatedId(){
		assertNull( rate.getRatedId() );
	}
	
	@Test
	public void testEmptyRaterId(){
		assertNull( rate.getRaterId() );
	}
	
	@Test
	public void testEmptyText(){
		assertNull( rate.getText() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		rate.setId(testId);
		assertEquals( testId, rate.getId() );
	}
	
	@Test
	public void testSetAndGetPositive(){
		BigInteger testPositive = BigInteger.valueOf(1);
		rate.setPositive( testPositive);
		assertEquals(  testPositive, rate.getPositive() );
	}
	
	@Test
	public void testSetAndGetProductId(){
		BigInteger testProductId = BigInteger.valueOf(1);
		rate.setProductId(testProductId);
		assertEquals( testProductId, rate.getProductId() );
	}
	
	@Test
	public void testSetAndGetRatedId(){
		BigInteger testRatedId = BigInteger.valueOf(1);
		rate.setRatedId(testRatedId);
		assertEquals( testRatedId, rate.getRatedId() );
	}
	
	@Test
	public void testSetAndGetRaterId(){
		BigInteger testRaterId = BigInteger.valueOf(1);
		rate.setRaterId(testRaterId);
		assertEquals( testRaterId, rate.getRaterId() );
	}
	
	@Test
	public void testSetAndGetText(){
		String testText = "Ez egy értékelés";
		rate.setText(testText);
		assertEquals( testText, rate.getText() );
	}
	
}
