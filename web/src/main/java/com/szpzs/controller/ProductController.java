package com.szpzs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@Autowired
	ServletContext servletContext;

	@ResponseBody @RequestMapping(value = "/productUpload", method=RequestMethod.POST)
	public String productUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = (Product) mapper.readValue(request.getParameter("product"), Product.class);
		
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> map = multipartRequest.getMultiFileMap();
			
			if(map != null) {
				String webappDatasRoot = servletContext.getRealPath("../GoodsExchangePublic/images");
				List<String> fileNames = new ArrayList<String>();
				Iterator iter = map.keySet().iterator();
				while(iter.hasNext()) {
					String str = (String) iter.next();

					List<MultipartFile> fileList =  map.get(str);
					for(MultipartFile file : fileList) {
							String filePath = webappDatasRoot + "/" + file.getOriginalFilename();
					    File dest = new File(filePath);
					    if(!dest.exists()){
					    	dest.mkdirs();
						}
					    file.transferTo(dest);
					    fileNames.add(file.getOriginalFilename());
					}
				}
				result = productService.saveProduct(product, fileNames);
				
				return result;
			}
		}
		return "Hiba történt a termék mentése közben!";
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
