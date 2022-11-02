package com.capgemini.library.model;

public class BookContents {
	
	private String bookId;
	private String synopsis;

	
	public BookContents(String bookId, String synopsis) {
		super();
		this.bookId = bookId;
		this.synopsis = synopsis;
	}
	//getters and setters
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	

}
