package com.szpzs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szpzs.service.*;

@Controller
public class HelloController {
	@Autowired
	protected Hello hello;
	
	@RequestMapping(value = "/")
	 public void vmi(){
		System.out.println( "Beleptem a vmibe." );
		hello.msg();
	}

}
