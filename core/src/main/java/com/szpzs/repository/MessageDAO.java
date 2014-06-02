package com.szpzs.repository;

import java.math.BigInteger;
import java.util.List;

import com.szpzs.model.Message;


public interface MessageDAO {
	
	public void SaveMessage(Message message);
	public Message GetMessage(long id);
	public List <Message> ListAllMessage(BigInteger personid);
}
