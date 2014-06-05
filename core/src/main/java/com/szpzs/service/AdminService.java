package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Categories;

public interface AdminService {
	
	public List<Categories> getCategoryList();
	
	public Categories getCategory(Long id);
	
	public void saveCategory(Categories category);
	
	public void updateCategory(Categories category);
	
	public void removeCategory(Long id);
}
