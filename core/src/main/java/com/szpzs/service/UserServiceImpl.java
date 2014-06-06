package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Role;
import com.szpzs.model.User;
import com.szpzs.repository.UserDAO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void addUser(User user){
		if(userDAO.existsUser(user.getUserName()) == false){
			if(user.getStatus() == null){
				user.setStatus(BigInteger.valueOf(1));
			}
			if(user.getRole() == null){
				Role role = userDAO.getRoleByName("user");
				user.setRole(role);
			}
			userDAO.saveUser(user);
		}else{
			System.out.println("This is exists.");
		}
	}
	
	@Override
	@Transactional
	public User getUserById(int id){		
		User user = userDAO.getUserById((long) id);
		return user;
	}
	
	/*@Override
	@Transactional
	public void deleteUserById(int id){		
		userDAO.deleteUserById((long) id);
	}*/
	
	@Override
	@Transactional
	public List<User> getAllUser(){		
		return userDAO.getAllUser();
	}
	
	@Override
	@Transactional
	public void updateUser(User newuser){
		userDAO.updateUser(newuser);
	}

	@Override
	@Transactional
	public void inactivateUser(int id) {
		User user = userDAO.getUserById((long)id);
		user.setStatus(BigInteger.valueOf(0));
	}
	
	@Override
	@Transactional
	public void activateUser(int id) {
		User user = userDAO.getUserById((long)id);
		user.setStatus(BigInteger.valueOf(1));
	}
	
	@Override
	@Transactional
	public void updateUserRole(int id, String roleName) {
		Role role = userDAO.getRoleByName(roleName);
		if(role != null){
			User user = userDAO.getUserById((long)id);
			if(user != null){
				user.setRole(role);
			}
		}
	}
}
