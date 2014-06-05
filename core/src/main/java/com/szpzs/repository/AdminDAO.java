package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Categories;

public interface AdminDAO {
	
	public List<Categories> getCategoryList();
	
	public Categories getCategoryById(Long id);
	
	public void saveCategory(Categories category);
	
	public void updateCategory(Categories category);
	
	public void removeCategory(Long id);
}
