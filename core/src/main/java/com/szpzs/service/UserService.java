package com.szpzs.service;

import java.math.BigInteger;

import com.szpzs.model.Role;
import com.szpzs.model.User;

public interface UserService {
	
	public boolean existsUser(String userName);
	
	public String convertPasswordToMd5(String pass);
	
	public Role addRole();
	
	public User getUser(String userName,String password);
	
	public User getUserById(Long id);
	
	public String saveUser(User user);
	
	public String editUser(User user);

}
