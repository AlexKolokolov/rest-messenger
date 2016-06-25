package org.kolokolov.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
	
	private long id;
	private String message;
	private Date created;
	private String author;
	Map<Long, Comment> comments = new HashMap<>();
	List<Link> links = new ArrayList<>();
	
	public Message() {}

	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComment(Comment comment) {
		this.comments.put(comment.getId(), comment);
	}
	
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void setLink(Link link) {
		this.links.add(link);
	}
	
	public void addLink(String url, String rel) {
		links.add(new Link(url,rel));
	}
}
