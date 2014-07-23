package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Category;
import com.szpzs.repository.CategoryDAO;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> ListAllCategory() {
		return categoryDAO.ListAllCategory();
	}

	@Override
	public String SaveCategory(Category category) {
		return categoryDAO.SaveCategory(category);
	}


	@Override
	public List<Category> getCategories() {
		return categoryDAO.getCategories();
	}
	
	@Override
	public String saveCategory(Category category) {
		return categoryDAO.saveCategory(category);
	}
	
	@Override
	public String editCategory(Category category){
		return categoryDAO.editCategory(category);
	}
	
	@Override
	public String deleteCategory(Long id){
		return categoryDAO.deleteCategory(id);
	}
	
}
