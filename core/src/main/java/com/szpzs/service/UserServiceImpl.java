package com.szpzs.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.User;
import com.szpzs.repository.UserDAO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void addUser(User user){
		if(user.getStatus() == null){
			user.setStatus(BigInteger.valueOf(1));
		}
		if(user.getRole() == null){
			user.setRole(BigInteger.valueOf(1));
		}
		userDAO.saveUser(user);		
	}
}
