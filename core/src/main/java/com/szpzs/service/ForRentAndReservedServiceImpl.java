package com.szpzs.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;
import com.szpzs.repository.ForRentAndReservedDAO;

@Service("ForRentAndReservedServiceImpl")
public class ForRentAndReservedServiceImpl implements ForRentAndReservedService {
	
	@Autowired
	private ForRentAndReservedDAO forRentAndReservedDAO;
	
	@Override
	public String saveForRent(ForRent forRent){
		//check forRent is valid, isn't date range overlap
			forRentAndReservedDAO.saveForRent(forRent);
			return "ok";
	}
	
	@Override
	public List<ForRent> getForRentsByProduct(BigInteger productId) {
		return forRentAndReservedDAO.getForRentsByProductId(productId);
	}
}
