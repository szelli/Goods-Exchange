package com.szpzs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//import com.szpzs.model.Rate;
import com.szpzs.model.User;
import com.szpzs.model.County;
import com.szpzs.security.config.SecurityConfig;
import com.szpzs.service.CityService;
import com.szpzs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected CityService cityService;
	
	@Autowired
	protected SecurityConfig sc;
	
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
	public User loginUser(@RequestBody String userdatas, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		user = userService.getUser(user.getUserName(), user.getPassword());
		if(user != null) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication userAuth;
			try {
				userAuth = sc.authenticationManager().authenticate(token);
				context.setAuthentication(userAuth);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	@ResponseBody @RequestMapping(value = "/logoutRequest", method=RequestMethod.POST, produces = "application/json")
	public String logout() throws JsonParseException, JsonMappingException, IOException {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return "ok";
		} else {
			return "not ok";
		}
		/* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      if (auth != null){    
		         new SecurityContextLogoutHandler().logout(request, response, auth);
		         new PersistentTokenBasedRememberMeServices().logout(request, response, auth);
		      }
		*/
	}
	
	@ResponseBody @RequestMapping(value = "/getUserRequest", method=RequestMethod.POST, produces = "application/json")
	public User getUserById(@RequestBody String userdatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		user = userService.getUserById(user.getId());
		return user;
	}
	
	@ResponseBody @RequestMapping(value = "/editProfileRequest", method=RequestMethod.POST, produces = "application/json")
	public String editProfile(@RequestBody String userdatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		result = userService.editUser(user);
		return result;
	}
	
	@ResponseBody @RequestMapping(value = "/validatePasswordRequest", method=RequestMethod.POST, produces = "application/json")
	public String validatePassword(@RequestBody String userdatas) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userdatas, User.class);
		result = userService.validatePassword(user.getId(), user.getPassword());
		return result;
	}
	
	@ResponseBody @RequestMapping(value = "/getCountiesRequest", method=RequestMethod.POST, produces = "application/json")
	public List<County> getCounty() throws JsonParseException, JsonMappingException, IOException {
		return cityService.getCounties();
		//return counties;
	}
}
	
/*		
 * 		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("g:\\goods/user.json"), User.class);
		Map <String, User> map = userService.getUser(user.getUserName(), user.getPassword());
		for(String key: map.keySet())
			System.out.println(key + " - " + map.get(key));
	}*/
	
/*
	
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
	
}
*/