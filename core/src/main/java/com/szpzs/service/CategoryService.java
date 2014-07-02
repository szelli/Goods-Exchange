package com.szpzs.service;

import java.util.List;

import com.szpzs.model.Categories;

public interface CategoryService {

	public List<Categories> ListAllCategory();
	public String SaveCategory(Categories category);
}
