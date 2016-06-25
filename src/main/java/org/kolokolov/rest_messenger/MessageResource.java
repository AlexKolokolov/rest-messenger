package org.kolokolov.rest_messenger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.kolokolov.model.Message;
import org.kolokolov.service.MessageService;

@Path("messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
    @Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public List<Message> getMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size) {
		if (year > 0) return messageService.getAllMessagesByYear(year);
		if (start > 0 || size > 0) return messageService.getAllMessagePaginated(start, size);
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.clearLinks();
		message.addLink(String.valueOf(uriInfo.getAbsolutePath()), "self");
		message.addLink(getProfileUri(uriInfo, message), "profile");
		message.addLink(getCommentsUri(uriInfo, message), "comments");
		return message;
	}

	private String getCommentsUri(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().
				path(MessageResource.class).
				path(MessageResource.class, "getCommentResource").
				resolveTemplate("messageId", message.getId()).
				path(CommentResource.class).
				build();
		return String.valueOf(uri);
	}

	private String getProfileUri(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return String.valueOf(uri);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message responseMessage = messageService.addMessage(message);
		return Response.
				created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(responseMessage.getId())).build()).
				status(Status.CREATED).
				entity(responseMessage).
				build();
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		if (messageService.getMessage(messageId) == null) return null;
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
