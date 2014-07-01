package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.ForRent;

public interface ForRentAndReservedDAO {
	public void saveForRent(ForRent forRent);
	public List<ForRent> getForRentsByProductId(BigInteger productId);
	void deleteForRent(ForRent forRent);
}
