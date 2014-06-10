package com.szpzs.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;

public interface ForRentAndReservedDAO {
	public void saveForRent(ForRent forRent);
	//public List<ForRent> getForRentsByUser(Long id);
	//public Boolean existsForRent(BigInteger productId);
	//public void updateForRent(ForRent forRent);
	public void deleteForRent(Long id);
	public List<ForRent> getForRentsByProductId(BigInteger productId);
	public List<ForRent> getReservedByProductId(BigInteger productId);
	public void saveReserved(Reserved reserved);
	public ForRent getForRentByDateInterval(BigInteger productId, Date begin, Date end);
}
