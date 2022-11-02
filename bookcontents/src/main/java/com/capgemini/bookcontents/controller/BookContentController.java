package com.capgemini.bookcontents.controller;

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

import com.capgemini.bookcontents.exception.BookNotFoundException;
import com.capgemini.bookcontents.exception.NoContentEntryException;
import com.capgemini.bookcontents.model.BookContents;
import com.capgemini.bookcontents.service.BookContentService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookstore/content")
public class BookContentController {
	
	@Autowired
	private BookContentService service;
	
	@GetMapping("/get/{bookid}")
	public ResponseEntity<Optional<BookContents>> getBookById(@PathVariable("bookid") String bookid) throws BookNotFoundException {
		return new ResponseEntity<Optional<BookContents>>(service.getBookContentByBookId(bookid), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<BookContents> addBooks(@RequestBody BookContents cont) throws NoContentEntryException {
		return new ResponseEntity<BookContents>(service.addBookContent(cont), HttpStatus.OK);
	}
	
	@PutMapping("/update/{bookid}")
	public ResponseEntity<String> updateUser(@PathVariable("bookid") String bookid, @RequestBody BookContents cont) {
		service.updateContent(bookid, cont);
		return new ResponseEntity<String>("Content updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{bookid}")
	public ResponseEntity<String> deleteUser(@PathVariable("bookid") String bookid) {
		String msg = "Deleted Succesfully ID: " + bookid;
		service.deleteContent(bookid);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
