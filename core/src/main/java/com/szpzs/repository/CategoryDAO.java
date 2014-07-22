package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Categories;

public interface CategoryDAO {

	public List<Categories> ListAllCategory();
	public String SaveCategory(Categories category);
}
