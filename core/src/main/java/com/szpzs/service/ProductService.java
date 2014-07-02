package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;
import com.szpzs.model.ProductListDatas;

public interface ProductService {
	
	public Product getProduct(long id);
	
	public String saveProduct(Product product);
	
	public boolean existsProduct(Product product);
	
	public List<Product> getProductList(ProductListDatas datas);
}
