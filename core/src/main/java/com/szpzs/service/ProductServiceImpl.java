package com.szpzs.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Product;
import com.szpzs.repository.ProductDao;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product getProduct(long id) {
		Product product = productDao.getProduct(id);
		return product;
	}

	@Override
	public String saveProduct(Product product) {
		product.setStatus(BigInteger.valueOf(1));
		productDao.saveProduct(product);	
		if (existsProduct(product)){
			return "ok";
		} else {
			return "product not saved";
		}
	}
	
	@Override
	public boolean existsProduct(Product product) {
		if (productDao.existsProduct(product)){
			return true;
		} else {
			return false;
		}
	}

}
