package com.szpzs.repository;

import java.math.BigInteger;
import java.util.Collection;

import com.szpzs.model.City;
import com.szpzs.model.User;

public interface UserDAO {

	public User getUser (String userName, String password);
	
	public User getUserById(Long id);
	
	public void saveUser(User user);
	
	public String editUser(User user);
	
	public boolean validatePassword(Long id, String password);
	
	public boolean existsUser(String userName);
	
	public String convertPasswordToMd5(String pass);

	public String deleteUser(Long id);
}
