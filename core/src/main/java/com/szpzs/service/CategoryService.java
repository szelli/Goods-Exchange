package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Category;

public interface CategoryService {

	public List<Category> ListAllCategory();
	
	public String SaveCategory(Category category);
	
	public List<Category> getCategories();

	public String saveCategory(Category category);

	public String editCategory(Category category);

	public String deleteCategory(Long id);
}
