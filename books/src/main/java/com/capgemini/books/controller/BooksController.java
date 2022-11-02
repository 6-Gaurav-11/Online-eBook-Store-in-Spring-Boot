package com.capgemini.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.books.exception.BookNotAvailableException;
import com.capgemini.books.exception.EmptyInputException;
import com.capgemini.books.exception.NoBooksAvailableException;
import com.capgemini.books.model.Books;
import com.capgemini.books.service.BooksService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookstore/books")
public class BooksController {
	
	@Autowired
	private BooksService service;
	
	@PostMapping("/add")
	public ResponseEntity<Books> addBooks(@RequestBody Books book) throws EmptyInputException {
		return new ResponseEntity<Books>(service.addBooks(book), HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addBooks(book));
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Books>> getAllBooks() throws NoBooksAvailableException {
		return new ResponseEntity<List<Books>>(service.getAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{bookid}")
	public ResponseEntity<Optional<Books>> getBookById(@PathVariable("bookid") String bookid) throws BookNotAvailableException {
		return new ResponseEntity<Optional<Books>>(service.getBookById(bookid), HttpStatus.OK);
	}
	
	@GetMapping("/get/name/{id}")
	public ResponseEntity<Optional<List<Books>>> getBookByName(@PathVariable("id") String id) throws BookNotAvailableException {
		return new ResponseEntity<Optional<List<Books>>>(service.getBookByName(id), HttpStatus.OK);
	}
	
	@GetMapping("/get/genre/{id}")
	public ResponseEntity<Optional<List<Books>>> getBookByGenre(@PathVariable("id") String id) throws BookNotAvailableException {
		return new ResponseEntity<Optional<List<Books>>>(service.getBooksByGenre(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{bookid}")
	public ResponseEntity<String> delete(@PathVariable("bookid") String bookid) throws BookNotAvailableException {
		String msg = "Deleted Succesfully ID: " + bookid;
		service.deleteBooks(bookid);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/update/{bookid}")
	public ResponseEntity<String> update(@PathVariable("bookid") String bookid, @RequestBody Books book) throws BookNotAvailableException {
		service.updateBooks(bookid, book);
		return new ResponseEntity<String>("Book updated succesfully !", HttpStatus.OK);
	}

}
