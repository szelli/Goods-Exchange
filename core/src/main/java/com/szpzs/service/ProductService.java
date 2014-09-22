package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;

public interface ProductService {
	
	public int getProductCount(Product datas);
	
	public Product getProduct(long id);
	
	public String saveProduct(Product product, List<String> fileNames);
	
	public String updateProduct(Product product);
	
	//public List<Product> getProductList(ProductListDatas datas);

	public List<Product> getProductsByOwner(BigInteger ownerId);

	public List<Product> getAllProduct(Product datas);


}
