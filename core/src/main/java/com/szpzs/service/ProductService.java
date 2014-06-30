package com.szpzs.service;

import com.szpzs.model.Product;

public interface ProductService {
	
	public Product getProduct(long id);
	
	public String saveProduct(Product product);
	
	public boolean existsProduct(Product product);
}
