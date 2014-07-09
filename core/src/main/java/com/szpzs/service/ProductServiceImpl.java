package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Product;
import com.szpzs.model.ProductListDatas;
import com.szpzs.repository.ProductDAO;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Product getProduct(long id) {
		Product product = productDAO.getProduct(id);
		return product;
	}

	@Override
	public String saveProduct(Product product) {
		product.setStatus(BigInteger.valueOf(1));
		return productDAO.saveProduct(product);		
	}

	@Override
	public List<Product> getProductList(ProductListDatas datas) {
		return productDAO.getProductsList(datas);
	}
	
	@Override
	public String updateProduct(Product product) {
		if(productDAO.getProduct(product.getId())!=null){
			product.setStatus(BigInteger.valueOf(1));
			return productDAO.updateProduct(product);	
		}
		else return "product not updated";
	} 

}
