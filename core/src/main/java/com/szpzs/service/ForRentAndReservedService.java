package com.szpzs.service;

import java.util.List;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;

public interface ForRentAndReservedService {
	public void saveForRent(ForRent forRent);
	//public void updateForRent(ForRent forRent);
	public List<ForRent> getForRentsByProduct(int productId);
	public void changedToReserved(Reserved reserved);
}