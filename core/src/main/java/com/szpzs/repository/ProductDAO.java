package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Product;
import com.szpzs.model.ProductListDatas;

public interface ProductDAO {
	
	public Product getProduct(long id);
	
	public void saveProduct(Product product);

	public boolean existsProduct(Product product);
	
	public int getProductsCountByOwner(BigInteger id);
	
	public List<Product> getProductsList(ProductListDatas datas);
	
	public int getProductsCount();
}
