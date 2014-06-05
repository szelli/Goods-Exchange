package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Category;

public interface AdminDAO {
	
	public List<Category> getCategoryList();
	
	public Category getCategoryById(Long id);
	
	public void saveCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void removeCategory(Long id);
}
