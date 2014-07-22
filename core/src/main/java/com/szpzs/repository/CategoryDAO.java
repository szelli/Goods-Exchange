package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Category;

public interface CategoryDAO {

	public List<Category> ListAllCategory();
	public String SaveCategory(Category category);
}
