package com.szpzs.service;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.ForRent;


public interface ForRentAndReservedService {
	
	public String saveForRent(ForRent forRent);
	
	public List<ForRent> getForRentsByProduct(BigInteger productId);
	
}
