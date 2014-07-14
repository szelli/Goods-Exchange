package com.szpzs.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Picture;
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
	public String saveProduct(Product product, List<String> fileNames) {
		product.setUploadTime(new Date());
		product.setStatus(BigInteger.valueOf(1));
		List<Picture> pictures = new ArrayList<Picture>();
		for(String link : fileNames){
			Picture picture = new Picture();
			picture.setLink(link);
			picture.setUploadTime(new Date());
			pictures.add(picture);
		}
		product.setPictures(pictures);
		productDAO.saveProduct(product);	
		if (existsProduct(product)){
			return "ok";
		} else {
			return "product not saved";
		}
	}
	
	@Override
	public boolean existsProduct(Product product) {
		if (productDAO.existsProduct(product)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Product> getProductList(ProductListDatas datas) {
		return productDAO.getProductsList(datas);
	}

}
