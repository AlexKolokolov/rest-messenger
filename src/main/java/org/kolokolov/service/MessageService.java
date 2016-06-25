package org.kolokolov.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.kolokolov.database.DataBaseClass;
import org.kolokolov.exception.DataNotFoundException;
import org.kolokolov.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DataBaseClass.getMessages();
	
	public MessageService() {
		addMessage(new Message(1L, "Hello, world", "kolokolov"));
		addMessage(new Message(2L, "Hello, jersey", "kolokolov"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesByYear(int year) {
		List<Message> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if (calendar.get(Calendar.YEAR) == year) {
				list.add(message);
			}
		}
		return list;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size) {
		List<Message> list = new ArrayList<>(messages.values());
		if (start > list.size()) return null;
		if (start + size > list.size()) return list.subList(start, list.size());
		return list.subList(start, start + size);
	}
	
	public Message getMessage(Long id) {
		Message message = messages.get(id);
		if (message == null) throw new DataNotFoundException("Message with id " + id + " not found");
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) return null;
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
