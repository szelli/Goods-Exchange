package com.szpzs.repository;


import static org.junit.Assert.*;

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

import com.szpzs.model.Product;
import com.szpzs.repository.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDAOImplTest {
	
	@Autowired
	private ProductDAO productDAO;
	
	private Product tempData(){
		Product product = new Product();
		product.setName("Termék");
		product.setCityId(BigInteger.valueOf(22));
		product.setArea(BigInteger.valueOf(2));
		product.setCategoryId(BigInteger.valueOf(2));
		product.setDescriptions("Ez egy Termék");
		product.setOwnerId(BigInteger.valueOf(2));
		product.setStatus(BigInteger.valueOf(1));
		return product;
	}
	
	private Product tempData1(){
		Product product = new Product();
		product.setName("Prod1");
		product.setCityId(BigInteger.valueOf(1));
		product.setArea(BigInteger.valueOf(10));
		product.setCategoryId(BigInteger.valueOf(1));
		product.setDescriptions("Ez egy prod1");
		product.setOwnerId(BigInteger.valueOf(1));
		product.setStatus(BigInteger.valueOf(1));
		return product;
	}
	
	private Product tempData2(){
		Product product = new Product();
		product.setName("Prod2");
		product.setCityId(BigInteger.valueOf(2));
		product.setArea(BigInteger.valueOf(20));
		product.setCategoryId(BigInteger.valueOf(2));
		product.setDescriptions("Ez egy prod2");
		product.setOwnerId(BigInteger.valueOf(1));
		product.setStatus(BigInteger.valueOf(0));
		return product;
	}
	
	private Product tempData3(){
		Product product = new Product();
		product.setName("Prod3");
		product.setCityId(BigInteger.valueOf(3));
		product.setArea(BigInteger.valueOf(30));
		product.setCategoryId(BigInteger.valueOf(3));
		product.setDescriptions("Ez egy prod3");
		product.setOwnerId(BigInteger.valueOf(3));
		product.setStatus(BigInteger.valueOf(1));
		return product;
	}

	@Test
	@Transactional
	public void test1GetProduct(){
		Product product = tempData();
		Product product2 = productDAO.getProduct((long) 2);
		assertEquals( product.getName(), product2.getName() );
		assertEquals( product.getCityId(), product2.getCityId() );
		assertEquals( product.getArea(), product2.getArea() );
		assertEquals( product.getCategoryId(), product2.getCategoryId() );
		assertEquals( product.getDescriptions(), product2.getDescriptions() );
		assertEquals( product.getOwnerId(), product2.getOwnerId() );
		assertEquals( product.getStatus(), product2.getStatus() );
	}
	
	@Test
	@Transactional
	public void test2GetProductList(){
		Product product1 = tempData1();
		Product product2 = tempData2();
		Product product3 = tempData3();
		
		productDAO.saveProduct(product1);
		productDAO.saveProduct(product2);
		productDAO.saveProduct(product3);
		List<Product> products = productDAO.getProductList(); 
		
		assertEquals(4, products.size());
	}

	
	@Test
	@Transactional
	public void test3SaveProduct(){
		Product product = tempData1();
		productDAO.saveProduct(product);
		
		List<Product> products = productDAO.getProductList();
		Product product2 = products.get(products.size()-1);
		
		assertEquals( product.getName(), product2.getName() );
		assertEquals( product.getCityId(), product2.getCityId() );
		assertEquals( product.getArea(), product2.getArea() );
		assertEquals( product.getCategoryId(), product2.getCategoryId() );
		assertEquals( product.getDescriptions(), product2.getDescriptions() );
		assertEquals( product.getOwnerId(), product2.getOwnerId() );
		assertEquals( product.getStatus(), product2.getStatus() );
	}
	
	@Test
	@Transactional
	public void test4UpdateStatus(){
		Product product1 = tempData1();
		Product product2 = tempData2();
		Product product3 = tempData3();
		
		Product product = productDAO.getProduct((long) 2);
		assertNotNull(product);
		
		if (product.getStatus() == BigInteger.valueOf(0)){
			product.setStatus(BigInteger.valueOf(1));
			assertEquals(BigInteger.valueOf(1), productDAO.getProduct((long) 2).getStatus());
		}else{
			product.setStatus(BigInteger.valueOf(0));
			assertEquals(BigInteger.valueOf(0), productDAO.getProduct((long) 2).getStatus());
		}	
	}
	
	@Test
	@Transactional
	public void test5UpdateProduct(){
		Product product2 = tempData2();
		product2.setId((long)2);
		
		
		Product oldProduct = productDAO.getProduct((long) 2);
		
		assertNotEquals(product2, oldProduct);
		product2.setName("Tibi");
		productDAO.update(product2);
		
		Product newProduct = productDAO.getProduct((long) 2);
		
		assertEquals( newProduct.getName(), product2.getName() );
		assertEquals( newProduct.getCityId(), product2.getCityId() );
		assertEquals( newProduct.getArea(), product2.getArea() );
		assertEquals( newProduct.getCategoryId(), product2.getCategoryId() );
		assertEquals( newProduct.getDescriptions(), product2.getDescriptions() );
		assertEquals( newProduct.getOwnerId(), product2.getOwnerId() );
		assertEquals( newProduct.getStatus(), product2.getStatus() );
	}
}
