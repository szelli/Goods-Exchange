package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Role;
import com.szpzs.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUser(String userName,String password);
	
	public User getUserByName(String username);
	
	public User getUserById(Long id);
	
	public boolean existsUser(String userName);
	
	public String convertPasswordToMd5(String pass);
	
	public Role addRole();
	
	public String saveUser(User user);
	
	public String editUser(User user);
	
	public String validatePassword(Long id, String password);

	public String deleteUser(Long id);
	
}
