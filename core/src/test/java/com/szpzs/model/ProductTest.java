package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	Product product;
	
	@Before
	public void setUp(){
		product = new Product();
	}

	@Test
	public void testEmptyid(){
		assertNull( product.getId() );
	}
	
	@Test
	public void testEmptyName(){
		assertNull( product.getName() );
	}
	
	@Test
	public void testEmptyArea(){
		assertNull( product.getArea() );
	}
	
	@Test
	public void testEmptyCategoryId(){
		assertNull( product.getCategoryId() );
	}
	
	@Test
	public void testEmptyCityId(){
		assertNull( product.getCityId() );
	}
	
	@Test
	public void testEmptyDescription(){
		assertNull( product.getDescriptions() );
	}
	
	@Test
	public void testEmptyOwnerId(){
		assertNull( product.getOwnerId());
	}
	
	@Test
	public void testEmptyStatus(){
		assertNull( product.getStatus());
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		product.setId(testId);
		assertEquals( testId, product.getId() );
	}
	
	@Test
	public void testSetAndGetName(){
		String testName = "Feri";
		product.setName(testName);
		assertEquals( testName, product.getName() );
	}
	
	@Test
	public void testSetAndGetArea(){
		BigInteger testArea = BigInteger.valueOf(1);
		product.setArea(testArea);
		assertEquals( testArea, product.getArea() );
	}
	
	@Test
	public void testSetAndGetCategoryId(){
		BigInteger testCategoryId = BigInteger.valueOf(1);
		product.setCategoryId(testCategoryId);
		assertEquals( testCategoryId, product.getCategoryId() );
	}
	
	@Test
	public void testSetAndGetCityId(){
		BigInteger testCityId = BigInteger.valueOf(1);
		product.setCityId(testCityId);
		assertEquals( testCityId, product.getCityId() );
	}
	
	@Test
	public void testSetAndGetDescription(){
		String testDescription = "Ez egy termék";
		product.setDescriptions(testDescription);
		assertEquals( testDescription, product.getDescriptions() );
	}
	
	@Test
	public void testSetAndGetOwnerId(){
		BigInteger testOwnerId = BigInteger.valueOf(1);
		product.setOwnerId(testOwnerId);
		assertEquals( testOwnerId, product.getOwnerId() );
	}
	
	@Test
	public void testSetAndGetStatus(){
		BigInteger testStatus = BigInteger.valueOf(1);
		product.setStatus(testStatus);
		assertEquals( testStatus, product.getStatus() );
	}

}
