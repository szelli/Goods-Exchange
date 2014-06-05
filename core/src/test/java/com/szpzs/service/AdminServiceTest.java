package com.szpzs.service;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Category;
import com.szpzs.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	
	private Category tempParentData1(){
		Category category = new Category();
		category.setName("1. Kategória");
		return category;
	}
	
	private Category tempParentData2(){
		Category category = new Category();
		category.setName("2. Kategória");
		return category;
	}
	
	private Category tempChildData1(){
		Category category = new Category();
		category.setName("1. Kategória 1. gyereke");
		category.setParentId(BigInteger.valueOf(1));
		return category;
	}
	
	private Category tempChildData2(){
		Category category = new Category();
		category.setName("1. Kategória 2. gyereke");
		category.setParentId(BigInteger.valueOf(1));
		return category;
	}
	
	@Test
	public void test1GetCategory(){
		Category category = tempParentData1();
		Category category2 = adminService.getCategory((long)1);
		assertEquals( category.getName(), category2.getName());
		assertEquals( category.getParentId(), category2.getParentId());
	}
	
	@Test
	public void test2SaveCategory(){
		Category category = tempParentData2();
		adminService.saveCategory(category);
		
		List<Category> categorys = adminService.getCategoryList();
		
		assertEquals(category.getName(), categorys.get(categorys.size()-1).getName());
		assertEquals(category.getParentId(), categorys.get(categorys.size()-1).getParentId());
	}
	
	
	@Test
	public void test3GetProductList(){
		Category category1 = tempParentData1();
		Category category2 = tempChildData1();
		Category category3 = tempChildData2();
		
		adminService.saveCategory(category1);
		adminService.saveCategory(category2);
		adminService.saveCategory(category3);
		List<Category> categorys = adminService.getCategoryList(); 
		
		assertEquals(4, categorys.size());
	}
	
	@Test
	@Transactional
	public void test4UpdateCategory(){
		Category category = tempParentData1();
		Category category2 = adminService.getCategory((long)1);
		assertEquals( category.getName(), category2.getName());
		assertEquals( category.getParentId(), category2.getParentId());
		
		category.setName("Valami");
		adminService.updateCategory(category);
		
		Category updatedCategory = adminService.getCategory((long)1);
		
		assertEquals( category.getName(), updatedCategory.getName());
		assertEquals( category.getParentId(), updatedCategory.getParentId());		
	}
	
	@Test
	@Transactional
	public void test5RemoveCategory(){
		int size = adminService.getCategoryList().size()-1;
		Category category = adminService.getCategory((long)1);
		
		adminService.removeCategory(category.getId());
		assertEquals(size, adminService.getCategoryList().size());
	}
}
