package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CityTest {
	
	City city;
	
	@Before
	public void setUp(){
		city = new City();
	}

	@Test
	public void testEmptyid(){
		assertNull( city.getId() );
	}
	
	@Test
	public void testEmptyCity(){
		assertNull( city.getCity() );
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		city.setId(testId);
		assertEquals( testId, city.getId() );
	}
	
	@Test
	public void testSetAndGetName(){
		String testCity = "Pécs";
		city.setCity(testCity);
		assertEquals( testCity, city.getCity() );
	}
	
}
