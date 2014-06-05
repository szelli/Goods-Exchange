package com.szpzs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User user;
	
	@Before
	public void setUp(){
		user = new User();
	}

	@Test
	public void testEmptyid(){
		assertNull( user.getId() );
	}
	
	@Test
	public void testEmptyAddress(){
		assertNull( user.getAddress() );
	}
	
	@Test
	public void testEmptyCity(){
		assertNull( user.getCity() );
	}
	
	@Test
	public void testEmptyEmail(){
		assertNull( user.getEmail() );
	}

	@Test
	public void testEmptyFullName(){
		assertNull( user.getFullName() );
	}
	
	@Test
	public void testEmptyPassword(){
		assertNull( user.getPassword() );
	}
	
	@Test
	public void testEmptyPostcode(){
		assertNull( user.getPostcode());
	}

	@Test
	public void testEmptyUserName(){
		assertNull( user.getUserName());
	}
	
	@Test
	public void testEmptyRole(){
		assertNull( user.getRole());
	} 
	
	@Test
	public void testEmptyStatus(){
		assertNull( user.getStatus());
	}
	
	@Test
	public void testSetAndGetId(){
		Long testId =  (long) 1;
		user.setId(testId);
		assertEquals( testId, user.getId() );
	}
	
	@Test
	public void testSetAndGetAddress(){
		String testAddress = "Ez egy cím";
		user.setAddress(testAddress);
		assertEquals( testAddress, user.getAddress() );
	}
	
	@Test
	public void testSetAndGetCity(){
		String testCity = "Ez egy város";
		user.setCity(testCity);
		assertEquals( testCity, user.getCity() );
	}
	
	@Test
	public void testSetAndGetEmail(){
		String testEmail = "Ez egy emailcím";
		user.setEmail(testEmail);
		assertEquals( testEmail, user.getEmail() );
	}
	
	@Test
	public void testSetAndGetFullName(){
		String testFullName = "Ez egy teljes név";
		user.setFullName(testFullName);
		assertEquals( testFullName, user.getFullName() );
	}
	
	@Test
	public void testSetAndGetPassword(){
		String testPassword = "Ez egy jelszó";
		user.setPassword(testPassword);
		assertEquals( testPassword, user.getPassword() );
	}
	
	@Test
	public void testSetAndGetPostcode(){
		BigInteger testPostcode = BigInteger.valueOf(1);
		user.setPostcode(testPostcode);
		assertEquals( testPostcode, user.getPostcode() );
	}
	
	@Test
	public void testSetAndGetUserName(){
		String testUserName = "Ez egy username";
		user.setUserName(testUserName);
		assertEquals( testUserName, user.getUserName() );
	}
	
	@Test
	public void testSetAndGetRole(){
		BigInteger testRole = BigInteger.valueOf(1);
		user.setRole(testRole);
		assertEquals( testRole, user.getRole() );
	}
	
	@Test
	public void testSetAndGetStatus(){
		BigInteger testStatus = BigInteger.valueOf(1);
		user.setStatus(testStatus);
		assertEquals( testStatus, user.getStatus() );
	}

}
