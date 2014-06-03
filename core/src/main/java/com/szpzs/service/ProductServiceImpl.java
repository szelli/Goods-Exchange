package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.Product;
import com.szpzs.repository.ProductDAO;
import com.szpzs.repository.ProductDAOImpl;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public Product getProduct(Long id) {
		return productDAO.getProduct(id);
	}

	@Override
	@Transactional
	public List<Product> getProductList() {
		return productDAO.getProductList();
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productDAO.saveProduct(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	@Override
	public BigInteger checkProductStatus(Long id, BigInteger status) {
		if (status == BigInteger.valueOf(0)){
			return BigInteger.valueOf(0);
		} else {
			return BigInteger.valueOf(1);
		}
	}

	@Override
	@Transactional
	public void setProductStatus(Product product) {
		getProduct(product.getId()).setStatus(checkProductStatus(product.getId(), product.getStatus()));
	}


}
