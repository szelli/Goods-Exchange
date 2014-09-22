package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.User;

public interface UserDAO {
	
	public List<User> getAllUsers();
	
	public User getUser (String userName, String password);
	
	public User getUserByName(String username);
	
	public User getUserById(Long id);
	
	public void saveUser(User user);
	
	public String editUser(User user);
	
	public boolean validatePassword(Long id, String password);
	
	public boolean existsUser(String userName);
	
	public String convertPasswordToMd5(String pass);

	public String deleteUser(Long id);

}
