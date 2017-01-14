package org.kolokolov.model;

public class Link {
	
	private String url;
	private String rel;
	
	public Link() {}

	public Link(String link, String rel) {
		this.url = link;
		this.rel = rel;
	}

	public String getLink() {
		return url;
	}
	
	public void setLink(String link) {
		this.url = link;
	}
	
	public String getRel() {
		return rel;
	}
	
	public void setRel(String rel) {
		this.rel = rel;
	}
}
