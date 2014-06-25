package com.szpzs.controller;

import java.io.IOException;
import java.math.BigInteger;
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

import com.szpzs.model.ForRent;
import com.szpzs.model.Product;
import com.szpzs.service.ForRentAndReservedService;

@Controller
public class ForRentAndReservedController {
	
	@Autowired
	protected ForRentAndReservedService forRentAndReservedService;
	
	@ResponseBody @RequestMapping(value = "/saveForRentRequest", method=RequestMethod.POST, produces = "application/json")	
	public String saveForRent(@RequestBody String forRentDatas) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		ForRent forRent = mapper.readValue(forRentDatas, ForRent.class);
		String status = forRentAndReservedService.saveForRent(forRent);
		return status;
	}
	
	@ResponseBody @RequestMapping(value = "/getForRentsByProduct", method=RequestMethod.POST, produces = "application/json")
	public List<ForRent> getForRentsByProduct(@RequestBody String productDatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(productDatas, Product.class);
		List<ForRent> forRents= forRentAndReservedService.getForRentsByProduct(BigInteger.valueOf(product.getId()));
		return forRents;
	}
}
