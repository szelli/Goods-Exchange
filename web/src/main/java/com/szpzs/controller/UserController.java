package com.szpzs.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



//import com.szpzs.model.Product;
//import com.szpzs.model.Rate;
import com.szpzs.model.User;
//import com.szpzs.service.*;
import com.szpzs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	protected UserService userService;
	
	/*	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected RateService rateService;*/
	
	private String result;
	
	@ResponseBody @RequestMapping(value = "/registrationRequest",  method=RequestMethod.POST, produces = "application/json")
	public String regUser(@RequestBody String userdatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		result = userService.saveUser(user);
		return result;
	}
	
	@ResponseBody @RequestMapping(value = "/loginRequest", method=RequestMethod.POST, produces = "application/json")
	public User loginUser(@RequestBody String userdatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		user = userService.getUser(user.getUserName(), user.getPassword());
		return user;
	}
/*		
 * 		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user.json"), User.class);
		Map <String, User> map = userService.getUser(user.getUserName(), user.getPassword());
		for(String key: map.keySet())
			System.out.println(key + " - " + map.get(key));
	}*/
	
/*	@RequestMapping(value = "/updateRequest", method=RequestMethod.GET)
	public @ResponseBody void updateUser() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user.json"), User.class);
		userService.updateUser(user);
		System.out.println("Update Success");
	}
	
	@RequestMapping(value = "/deleteRequest", method=RequestMethod.GET)
		public @ResponseBody void deleteUser() throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(new File("g:\\goods/user.json"), User.class);
			result = userService.setStatusInactive(user);
			System.out.println("Result");
	}
	
	@RequestMapping(value = "/profileProducts", method=RequestMethod.GET)
	public @ResponseBody void getProfileProducts() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user2.json"), User.class);
		Map <String, List<Product>> map = productService.getProductListByOwner(BigInteger.valueOf(user.getId()));
		 for(String key: map.keySet())
			 System.out.println(key + " - " + map.get(key));

	}
	
	@RequestMapping(value = "/profileRates", method=RequestMethod.GET)
	public @ResponseBody void getProfileRates() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user2.json"), User.class);
		Map <String, List<Rate>> map = rateService.getRatesListByRated(BigInteger.valueOf(user.getId()));
		 for(String key: map.keySet())
			 System.out.println(key + " - " + map.get(key));
	}
	
	@RequestMapping(value = "/profileRatesCount", method=RequestMethod.GET)
	public @ResponseBody void getProfileRatesCount() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user2.json"), User.class);
		int[] counts = new int[3];
		counts[0] = rateService.getRatesCount(BigInteger.valueOf(user.getId()));
		counts[1] = rateService.getPositiveCount(BigInteger.valueOf(user.getId()));
		counts[2] = rateService.getNegativeCount(BigInteger.valueOf(user.getId()));
		System.out.println(counts[0]+"Pozitiv:"+counts[1]+"Negatív:"+counts[2]);
	}
	
	@RequestMapping(value = "/addProduct", method=RequestMethod.GET)
	public @ResponseBody void addProduct() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(new File("g:\\goods/product.json"), Product.class);
		result = productService.saveProduct(product);
		System.out.println(result);
	}
	
	@RequestMapping(value = "/updateProduct", method=RequestMethod.GET)
	public @ResponseBody void updateProduct() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(new File("g:\\goods/product2.json"), Product.class);
		result = productService.updateProduct(product);
		System.out.println(result);
	}
	
	@RequestMapping(value = "/deleteProduct", method=RequestMethod.GET)
	public @ResponseBody void deleteProduct() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(new File("g:\\goods/product2.json"), Product.class);
		result = productService.setStatusInactive(product);
		System.out.println(result);
	}*/
}
