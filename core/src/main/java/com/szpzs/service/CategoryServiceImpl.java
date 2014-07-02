package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Categories;
import com.szpzs.repository.CategoryDAO;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Categories> ListAllCategory() {
		return categoryDAO.ListAllCategory();
	}

	@Override
	public String SaveCategory(Categories category) {
		return categoryDAO.SaveCategory(category);
	}

	
	
}
