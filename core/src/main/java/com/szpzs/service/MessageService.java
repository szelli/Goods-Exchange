package com.szpzs.service;

import java.math.BigInteger;

import com.szpzs.model.Message;

public interface MessageService {

	public void SaveMessage(Message message);
	public void SenderDeleteMsg(long id);
	public void ReceiverDeleteMsg(long id);
	public void SetAsRead(long id);
	public void ListAllMessage(BigInteger personid);

}
