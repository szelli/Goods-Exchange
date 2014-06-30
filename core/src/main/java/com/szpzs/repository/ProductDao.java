package com.szpzs.repository;

import com.szpzs.model.Product;

public interface ProductDao {
	
	public Product getProduct(long id);
	
	public void saveProduct(Product product);

	public boolean existsProduct(Product product);
}
