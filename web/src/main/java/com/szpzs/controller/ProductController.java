package com.szpzs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szpzs.model.Categories;
import com.szpzs.model.City;
import com.szpzs.model.Product;
import com.szpzs.model.ProductListDatas;
import com.szpzs.service.CategoryService;
import com.szpzs.service.CityService;
import com.szpzs.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CategoryService categoryService;
	
	private String result;
	
	@ResponseBody @RequestMapping(value = "/productUpload",  method=RequestMethod.POST, produces = "application/json")
	public String productUpload(@RequestBody String productdatas) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(productdatas, Product.class);
		result = productService.saveProduct(product);
		System.out.println(productService.getProduct(2));
		System.out.println(result);
		
		
		return result;
	}
	
	@ResponseBody @RequestMapping(value = "/cityResponse",  method=RequestMethod.GET, produces = "application/json")
	public List<City> citysend()throws JsonParseException, JsonMappingException, IOException {

		System.out.println(cityService.ListAllCity());

		return cityService.ListAllCity();
	}
	
	@ResponseBody @RequestMapping(value = "/categoryResponse",  method=RequestMethod.GET, produces = "application/json")
	public List<Categories> categorySend()throws JsonParseException, JsonMappingException, IOException {

		System.out.println(cityService.ListAllCity());

		return categoryService.ListAllCategory();
	}
	
	@ResponseBody @RequestMapping(value = "/productList", method=RequestMethod.GET, produces = "application/json")
	public List<Product> getProductList() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ProductListDatas datas = mapper.readValue(new File("g:\\goods/datas.json"), ProductListDatas.class);
		List<Product> products =productService.getProductList(datas); 
		if (products == null){
			System.out.println("null");
		} else {
			System.out.println(products.get(0).getName());
			System.out.println(products.size());
		}
		
		return null;
	}
}
