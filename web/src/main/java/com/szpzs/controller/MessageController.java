package com.szpzs.controller;

import com.szpzs.model.Message;

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

import com.szpzs.service.MessageService;
import com.szpzs.service.UserService;

@Controller
public class MessageController {

	@Autowired
	protected MessageService messageService;
	@Autowired
	private UserService userService;
	
	String result;
	
	@ResponseBody @RequestMapping(value = "/getMessagesRequest", method=RequestMethod.POST, produces = "application/json")
	public List<Message> getMessages(@RequestBody String requestDatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Message datas = mapper.readValue(requestDatas, Message.class);
		List<Message> messages = messageService.getMessages(datas);
		return messages;
	}
	
	@ResponseBody @RequestMapping(value = "/saveMessageRequest",  method=RequestMethod.POST, produces = "application/json")
	public String saveMessage(@RequestBody String datas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(datas, Message.class);
		result = messageService.saveMessage(message);
		return result;
	}
	
	@ResponseBody @RequestMapping(value = "/changeStatusRequest",  method=RequestMethod.POST, produces = "application/json")
	public String changeStatus(@RequestBody String datas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(datas, Message.class);
		result = messageService.changeStatus(message);
		return result;
	}
	
	
	/*>@ResponseBody @RequestMapping(value = "/deleteMessageRequest", method=RequestMethod.POST, produces = "application/json")
	public String deleteMessage(@RequestBody String id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(id, Message.class);
		return messageService.deleteMessage(message.getId());
	}*/
	
	
	/*
	@ResponseBody @RequestMapping(value = "/getProductsByOwner", method=RequestMethod.POST, produces = "application/json")
	public List getProductsByOwner(@RequestBody String id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Product product = mapper.readValue(id, Product.class);
		List<Product> products = productService.getProductsByOwner(product.getOwnerId()); 
		return products;
	}
	
	
	@ResponseBody @RequestMapping(value = "/saveCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String saveCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.saveCategory(category);
	}
	
	@ResponseBody @RequestMapping(value = "/editCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String editCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.editCategory(category);

	}
	
	@ResponseBody @RequestMapping(value = "/deleteCategoryRequest", method=RequestMethod.POST, produces = "application/json")
	public String deleteCategory(@RequestBody String categorydatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Category category = mapper.readValue(categorydatas, Category.class);
		return categoryService.deleteCategory(category.getId());

	}*/
	
}