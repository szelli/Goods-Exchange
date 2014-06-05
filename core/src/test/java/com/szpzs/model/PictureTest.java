package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class PictureTest {
	
	Picture picture;
	
	@Before
	public void setUp(){
		picture = new Picture();
	}

	@Test
	public void testEmptyid(){
		assertNull( picture.getId() );
	}
	
	@Test
	public void testEmptyLink(){
		assertNull( picture.getLink() );
	}
	
	@Test
	public void testEmptyProductId(){
		assertNull( picture.getProductId() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		picture.setId(testId);
		assertEquals( testId, picture.getId() );
	}
	
	@Test
	public void testSetAndGetLink(){
		String testLink = "Ez egy link";
		picture.setLink(testLink);
		assertEquals( testLink, picture.getLink() );
	}
	
	@Test
	public void testSetAndGetProductId(){
		BigInteger testProductId = BigInteger.valueOf(1);
		picture.setProductId(testProductId);
		assertEquals( testProductId, picture.getProductId() );
	}
}
