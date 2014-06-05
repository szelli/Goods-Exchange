package com.szpzs.repository;

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

import com.szpzs.model.Categories;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminDAOImplTest {

	@Autowired
	private AdminDAO adminDAO;
	
	private Categories tempParentData1(){
		Categories category = new Categories();
		category.setName("1. Kategória");
		return category;
	}
	
	private Categories tempParentData2(){
		Categories category = new Categories();
		category.setName("2. Kategória");
		return category;
	}
	
	private Categories tempChildData1(){
		Categories category = new Categories();
		category.setName("1. Kategória 1. gyereke");
		category.setParentId(BigInteger.valueOf(1));
		return category;
	}
	
	private Categories tempChildData2(){
		Categories category = new Categories();
		category.setName("1. Kategória 2. gyereke");
		category.setParentId(BigInteger.valueOf(1));
		return category;
	}
	
	@Test
	@Transactional
	public void test1GetCategory(){
		Categories category = tempParentData1();
		Categories category2 = adminDAO.getCategoryById((long)2);
		assertEquals( category.getName(), category2.getName());
		assertEquals( category.getParentId(), category2.getParentId());
	}
}
	
/*	@Test
	@Transactional	
	public void test2SaveCategory(){
		Category category = tempParentData2();
		adminDAO.saveCategory(category);
		
		List<Category> categorys = adminDAO.getCategoryList();
		
		assertEquals(category.getName(), categorys.get(categorys.size()-1).getName());
		assertEquals(category.getParentId(), categorys.get(categorys.size()-1).getParentId());
	}
	
	@Test
	@Transactional
	public void test3GetProductList(){
		Category category1 = tempParentData1();
		Category category2 = tempChildData1();
		Category category3 = tempChildData2();
		
		adminDAO.saveCategory(category1);
		adminDAO.saveCategory(category2);
		adminDAO.saveCategory(category3);
		List<Category> categorys = adminDAO.getCategoryList(); 
		
		assertEquals(4, categorys.size());
	}
	
	@Test
	@Transactional
	public void test4UpdateCategory(){
		Category category = tempParentData1();
		Category category2 = adminDAO.getCategoryById((long)1);
		assertEquals( category.getName(), category2.getName());
		assertEquals( category.getParentId(), category2.getParentId());
		
		category.setName("Valami");
		adminDAO.updateCategory(category);
		
		Category updatedCategory = adminDAO.getCategoryById((long)1);
		
		assertEquals( category.getName(), updatedCategory.getName());
		assertEquals( category.getParentId(), updatedCategory.getParentId());		
	}
	
	@Test
	@Transactional
	public void test5RemoveCategory(){
		int size = adminDAO.getCategoryList().size()-1;
		Category category = adminDAO.getCategoryById((long)1);
		
		adminDAO.removeCategory(category.getId());
		assertEquals(size, adminDAO.getCategoryList().size());
	}
}*/
