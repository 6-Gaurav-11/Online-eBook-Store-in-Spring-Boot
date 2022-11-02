package com.capgemini.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="library")
public class Library {

	@Id
	private String id;
	
	private String username;
	private String bookId;

	private String name;
	private String author;
	private String publisher;
	private String genre;
	private String description;
	private int pages;
	private int publishYear;
	private String imageUrl;
	private double price;
	
	//constructors
	public Library() {}
	public Library(String id, String username, String bookId) {
		super();
		this.id = id;
		this.username = username;
		this.bookId = bookId;
	}
	public Library(String id, String username, String bookId, String name, String author, String publisher,
			String genre, String description, int pages, int publishYear, String imageUrl, double price) {
		super();
		this.id = id;
		this.username = username;
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.description = description;
		this.pages = pages;
		this.publishYear = publishYear;
		this.imageUrl = imageUrl;
		this.price = price;
	}
	
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}