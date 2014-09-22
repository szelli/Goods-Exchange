package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;

public interface ProductDAO {
	
	public Product getProduct(long id);
	
	public String saveProduct(Product product);
	
	public String updateProduct(Product product);
	
	//public List<Product> getProductsList(ProductListDatas datas);
	
	public int getProductsCount(Product datas);

	public List<Product> getProductsByOwner(BigInteger ownerId);

	public List<Product> getAllProduct(Product datas);
}
