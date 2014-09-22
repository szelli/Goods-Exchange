package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Category;

public interface CategoryDAO {

	public List<Category> ListAllCategory();

	public String saveCategory(Category category);

	public String editCategory(Category category);

	public String deleteCategory(Long id);
	
	public boolean existCategory(String name);
}
