package com.ss.model;

public class Author {
	
	private Integer authorId;
	private String authorName;
	
	public Author() {}
	
	public Author(Integer a_id, String a_name)
	{
		authorId = a_id;
		authorName = a_name;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer a_id) {
		authorId = a_id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String a_name) {
		authorName = a_name;
	}

}
