package com.ss.model;

public class Book {
	
	private Integer bookId;
	private String bookName;
	private Integer bookAuthor;
	private Integer bookPublisher;
	
	public Book() {}
	
	public Book(Integer b_id, String b_name, Integer b_author, Integer b_publisher)
	{
		bookId = b_id;
		bookName = b_name;
		bookAuthor = b_author;
		bookPublisher = b_publisher;
	}

	
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer b_id) {
		bookId = b_id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String b_name) {
		bookName = b_name;
	}

	public Integer getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(Integer b_author) {
		bookAuthor = b_author;
	}

	public Integer getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(Integer b_publisher) {
		bookPublisher = b_publisher;
	}

}
