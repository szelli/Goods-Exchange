package com.szpzs.service;

import java.util.List;

import com.szpzs.model.User;

public interface UserService {
	public void addUser(User user);
	public User getUserById(int id);
	//public void deleteUserById(int id);
	public List<User> getAllUser();
	public void updateUser(User newuser);
	public void inactivateUser(int id);
	public void activateUser(int id);
}
