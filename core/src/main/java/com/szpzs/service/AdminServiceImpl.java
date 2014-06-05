package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Categories;
import com.szpzs.repository.AdminDAO;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	@Transactional
	public List<Categories> getCategoryList() {
		return adminDAO.getCategoryList();
	}

	@Override
	@Transactional
	public Categories getCategory(Long id) {
		return adminDAO.getCategoryById(id);
	}

	@Override
	@Transactional
	public void saveCategory(Categories category) {
		adminDAO.saveCategory(category);
	}

	@Override
	@Transactional
	public void updateCategory(Categories category) {
		adminDAO.updateCategory(category);
		
	}

	@Override
	@Transactional
	public void removeCategory(Long id) {
		adminDAO.removeCategory(id);
	}

}
