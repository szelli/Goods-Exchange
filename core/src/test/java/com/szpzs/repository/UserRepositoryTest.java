package com.szpzs.repository;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Role;
import com.szpzs.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {
	
	@Autowired
	private UserDAO userDAO;
	
	static Logger log = Logger.getLogger(UserDAO.class.getName());
	
	@Test
	@Transactional
	public void test1getUser(){
		User user = userDAO.getUserById((long)10);
		assertNotNull(user);
	}
	
	@Test
	@Transactional
	public void test2exsitsUser(){
		User user = userDAO.getUserById((long)10);
		assertTrue(userDAO.existsUser(user.getUserName()));		
	}
	
	@Test
	@Transactional
	public void test3deleteUser(){
		User user = userDAO.getUserById((long)10);
		assertTrue(userDAO.existsUser(user.getUserName()));
		userDAO.deleteUserById((long)10);
		assertFalse(userDAO.existsUser(user.getUserName()));		
	}
	
	@Test
	@Transactional
	public void test4saveUser(){
		assertFalse(userDAO.existsUser("newuser"));
		User user = new User();
		user.setUserName("newuser");
		user.setFullName("User Fullname");
		user.setPassword("123456");
		user.setCity("Town");
		user.setPostcode(BigInteger.valueOf(1245));		
		user.setAddress("Place street 21");
		user.setEmail("valami@valami.hu");
		userDAO.saveUser(user);
		assertTrue(userDAO.existsUser("newuser"));		
	}
	
	@Test
	@Transactional
	public void test5getUserList(){
		assertNotNull(userDAO.getAllUser());
	}
	
	@Test
	@Transactional
	public void test6updateUser(){		
		User newUser = new User();
		newUser.setId((long)10);
		newUser.setUserName("duser");
		newUser.setFullName("user fullname");
		newUser.setPassword("123456");
		newUser.setEmail("valami@valami.hu");
		newUser.setCity("Town");
		newUser.setAddress("Place street 21");
		newUser.setPostcode(BigInteger.valueOf(1245));
		Role role = userDAO.getRoleByName("user");
		newUser.setRole(role);
		newUser.setStatus(BigInteger.valueOf(1));
		assertNotEquals(newUser.getUserName(),userDAO.getUserById((long)10).getUserName());	
		userDAO.updateUser(newUser);
		User updated = userDAO.getUserById((long)10);
		assertEquals(updated.getUserName(),newUser.getUserName());	
	}

	@Test
	@Transactional
	public void test7getRole(){
		assertEquals(userDAO.getRoleByName("admin").getId(),1);
		assertEquals(userDAO.getRoleByName("valami"), null);
	}
	
	
}