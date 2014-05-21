package com.szpzs.service;

import org.springframework.stereotype.Service;

@Service("Hello")
public class Hello {
	
	public void msg(){
		System.out.println("Hello");
	}
}
