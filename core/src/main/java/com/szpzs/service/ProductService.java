package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;

public interface ProductService {
	public Product getProduct(Long id);
	
	public List<Product> getProductList();
	
	public void saveProduct (Product product);
	
	public void updateProduct (Product product);
	
	public BigInteger checkProductStatus (Long id, BigInteger status);
	
	public void setProductStatus (Product product);
}
