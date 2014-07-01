package com.szpzs.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import com.szpzs.model.ForRent;


public interface ForRentAndReservedService {
	
	public String saveForRent(ForRent forRent) throws ParseException;
	
	public List<ForRent> getForRentsByProduct(BigInteger productId);
	
}
