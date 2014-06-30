package com.szpzs.repository;

import java.math.BigInteger;

import com.szpzs.model.User;

public interface UserDAO {

	public User getUser (String userName, String password);
	
	public User getUserById(Long id);
	
	public void saveUser(User user);
	
	public void editUser(User user);
	
	public boolean existsUser(String userName);
}
