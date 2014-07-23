package com.szpzs.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Category;
import com.szpzs.model.User;
import com.szpzs.repository.AdminDAO;

@Service("AdminServiceImpl")
public class AdminServiceImpl  implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<User> getUsers() {
		return adminDAO.getUsers();
	}
	
	
}
