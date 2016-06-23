package org.kolokolov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kolokolov.database.DataBaseClass;
import org.kolokolov.model.Comment;
import org.kolokolov.model.ErrorMessage;
import org.kolokolov.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DataBaseClass.getMessages();
	
	private ErrorMessage notFoundMessage = new ErrorMessage("Not found", 404, "http://javabrains.koushik.org");
	private Response notFoundResponse = Response.status(Status.NOT_FOUND).entity(notFoundMessage).build();
	
	public List<Comment> getAllComments(long messageId) {
		Message message = messages.get(messageId);
		if (message == null) throw new WebApplicationException(notFoundResponse);
		return new ArrayList<Comment>(message.getComments().values());
	}
	
	public Comment getComment(Long messageId, Long commentId) {
		Message message = messages.get(messageId);
		if (message == null) throw new WebApplicationException(notFoundResponse);
		Comment comment = message.getComments().get(commentId);
		if (comment == null) throw new WebApplicationException(notFoundResponse);
		return comment;
	}
	
	public Comment addComment(Long messageId, Comment comment) {
		Message message = messages.get(messageId);
		if (message == null) throw new WebApplicationException(notFoundResponse);
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
