package com.capgemini.bookcontents.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="book_contents")
public class BookContents {
	
	@Id
	private String bookId;
	
	private String synopsis;

	public BookContents() {}
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
