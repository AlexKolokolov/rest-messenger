package org.kolokolov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kolokolov.database.DataBaseClass;
import org.kolokolov.model.Comment;
import org.kolokolov.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DataBaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Message message = messages.get(messageId);
		if (message == null) return null;
		return new ArrayList<Comment>(message.getComments().values());
	}
	
	public Comment getComment(Long messageId, Long commentId) {
		Message message = messages.get(messageId);
		if (message == null) return null;
		return message.getComments().get(commentId);
	}
	
	public Comment addComment(Long messageId, Comment comment) {
		Message message = messages.get(messageId);
		if (message == null) return null;
		comment.setId(message.getComments().size() + 1);
		message.setComment(comment);
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment) {
		if (comment.getId() <= 0) return null;
		Message message = messages.get(messageId);
		if (message == null) return null;
		message.setComment(comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if (message == null) return null;
		return message.getComments().remove(commentId);
	}
}
