package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.Message;
import com.szpzs.repository.MessageDAO;

@Service("MessageServiceImpl")
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public List<Message> getMessages(Message datas) {
		return messageDAO.getMessages(datas);
	}

	@Override
	public String saveMessage(Message message) {
		return messageDAO.saveMessage(message);
	}

	@Override
	public String changeStatus(Message message) {
		return messageDAO.changeStatus(message);
	}

	/*@Override
	public String deleteMessage(Long id) {
		return messageDAO.deleteMessage(id);
	}*/

}
