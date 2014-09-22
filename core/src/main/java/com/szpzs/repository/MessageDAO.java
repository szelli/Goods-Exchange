package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.Message;

public interface MessageDAO {
	
	public List<Message> getMessages(Message datas);

	public String saveMessage(Message message);

	public String changeStatus(Message message);

	//public String deleteMessage(Long id);

}
