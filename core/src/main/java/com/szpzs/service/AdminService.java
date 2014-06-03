package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Category;

public interface AdminService {
	
	public List<Category> getCategoryList();
	
	public Category getCategory(Long id);
	
	public void saveCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void removeCategory(Long id);
}
