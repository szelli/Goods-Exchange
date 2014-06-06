package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Role;
import com.szpzs.model.User;

public interface UserDAO {
	public void saveUser(User user);
	public User getUserById(Long id);
	public Boolean existsUser(String userName);
	public void deleteUserById(Long id);
	public List<User> getAllUser();
	public void updateUser(User newuser);
	public Role getRoleByName(String roleName);
}
