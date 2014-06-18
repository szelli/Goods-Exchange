package com.szpzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Role;
import com.szpzs.model.User;
import com.szpzs.repository.UserDAO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUser(String userName, String password) {
		return userDAO.getUser(userName, password);
	}

	@Override
	public String saveUser(User user) {
		if (!existsUser(user.getUserName())){
			user.setRole(addRole());
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

}
