package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Category;
import com.szpzs.repository.AdminDAO;

@Service("ProductServiceImpl")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	@Transactional
	public List<Category> getCategoryList() {
		return adminDAO.getCategoryList();
	}

	@Override
	@Transactional
	public Category getCategory(Long id) {
		return adminDAO.getCategoryById(id);
	}

	@Override
	@Transactional
	public void saveCategory(Category category) {
		adminDAO.saveCategory(category);
	}

	@Override
	@Transactional
	public void updateCategory(Category category) {
		adminDAO.updateCategory(category);
		
	}

	@Override
	@Transactional
	public void removeCategory(Long id) {
		adminDAO.removeCategory(id);
	}

}
