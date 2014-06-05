package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class CategoriesTest {
	
	Categories category;
	
	@Before
	public void setUp(){
		category = new Categories();
	}

	@Test
	public void testEmptyid(){
		assertNull( category.getId() );
	}
	
	@Test
	public void testEmptyName(){
		assertNull( category.getName() );
	}
	
	@Test
	public void testEmptyParentId(){
		assertNull( category.getParentId() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		category.setId(testId);
		assertEquals( testId, category.getId() );
	}
	
	@Test
	public void testSetAndGetName(){
		String testName = "Barkács";
		category.setName(testName);
		assertEquals( testName, category.getName() );
	}
	
	@Test
	public void testSetAndGetParentd(){
		BigInteger testParentId = BigInteger.valueOf(1);
		category.setParentId(testParentId);
		assertEquals( testParentId, category.getParentId() );
	}
}
