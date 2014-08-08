package com.szpzs.controller;

import com.szpzs.model.Category;
import com.szpzs.model.Product;
import com.szpzs.model.User;
import com.szpzs.service.AdminService;
import com.szpzs.service.CategoryService;
import com.szpzs.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szpzs.service.UserService;

@Controller
public class AdminController {

	@Autowired
	protected AdminService adminService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@ResponseBody @RequestMapping(value = "/getUsersRequest", method=RequestMethod.POST, produces = "application/json")
	public List getUsers() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = adminService.getUsers(); 
		return users;
	}
	
	@ResponseBody @RequestMapping(value = "/deleteUserRequest", method=RequestMethod.POST, produces = "application/json")
	public String getUsers(@RequestBody String id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(id, User.class);
		return userService.deleteUser(user.getId());
	}
	
	@ResponseBody @RequestMapping(value = "/getProductsByOwner", method=RequestMethod.POST, produces = "application/json")
	public List getProductsByOwner(@RequestBody String id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(id, Product.class);
		List<Product> products = productService.getProductsByOwner(product.getOwnerId()); 
		return products;
	}
	
	@ResponseBody @RequestMapping(value = "/getCategoriesRequest", method=RequestMethod.POST, produces = "application/json")
	public List getCategories() throws JsonParseException, JsonMappingException, IOException {
		return categoryService.getCategories();
	}
	
	@ResponseBody @RequestMapping(value = "/saveCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String saveCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.saveCategory(category);
	}
	
	@ResponseBody @RequestMapping(value = "/editCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String editCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.editCategory(category);

	}
	
	@ResponseBody @RequestMapping(value = "/deleteCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String deleteCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.deleteCategory(category.getId());

	}
	
}
	

