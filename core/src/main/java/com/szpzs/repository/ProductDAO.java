package com.szpzs.repository;

import com.szpzs.model.Product;

public interface ProductDAO {
	public Product getProductById(int id);
	
	public void addProduct(Product product);
}
