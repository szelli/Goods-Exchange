package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Category;

public interface CategoryService {

	public List<Category> ListAllCategory();
	public String SaveCategory(Category category);
}
