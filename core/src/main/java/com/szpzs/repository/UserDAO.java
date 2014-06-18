package com.szpzs.repository;

import com.szpzs.model.User;

public interface UserDAO {

	public User getUser (String userName, String password);
	
	public void saveUser(User user);
	
	public boolean existsUser(String userName);
}
