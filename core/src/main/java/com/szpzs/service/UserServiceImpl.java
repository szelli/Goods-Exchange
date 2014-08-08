package com.szpzs.service;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.szpzs.model.City;
import com.szpzs.model.Role;
import com.szpzs.model.User;
import com.szpzs.repository.UserDAO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUser(String userName, String password) {
		password = convertPasswordToMd5(password);
		return userDAO.getUser(userName, password);
	}
	
	@Override
	public User getUserById(Long id){
		return userDAO.getUserById(id);
	}
	
	@Override
	public String saveUser(User user) {
		if (!existsUser(user.getUserName())){
			user.setRole(addRole());
			user.setPassword(convertPasswordToMd5(user.getPassword()));
			userDAO.saveUser(user);
			if (existsUser(user.getUserName())){
				return "ok";
			} else {
				return "user not saved";
			}
		} else {
			return "user exist";
		}
	}

	@Override
	public String editUser(User user){
		return userDAO.editUser(user);
	}
	
	public String validatePassword(Long id, String password){
		password = convertPasswordToMd5(password);
		if(userDAO.validatePassword(id, password)){
			return "ok";
		} else {
			return null;
		}
	}
	
	public String deleteUser(Long id){
		return userDAO.deleteUser(id);
	}
	
	@Override
	public boolean existsUser(String userName) {
		if (userDAO.existsUser(userName)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Role addRole() {
		Role role = new Role();
		role.setId(2);
		role.setRole("user");
		return role;
	}

	@Override
	public String convertPasswordToMd5(String pass) {
		return new Md5PasswordEncoder().encodePassword( pass, null );
	}
	
}
