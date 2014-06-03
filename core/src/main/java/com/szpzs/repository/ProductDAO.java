package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;

public interface ProductDAO {
	
	public List<Product> getProductList();
	
	public void saveProduct(Product product);
	
	public Product getProduct(Long id);
	
	public void update (Product product);
}
