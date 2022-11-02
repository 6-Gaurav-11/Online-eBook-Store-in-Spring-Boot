package com.capgemini.library.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.library.exception.BookNotFoundException;
import com.capgemini.library.exception.EmptyInputException;
import com.capgemini.library.exception.LibraryEmptyException;
import com.capgemini.library.exception.UserNotFoundException;
import com.capgemini.library.model.BookContents;
import com.capgemini.library.model.Library;
import com.capgemini.library.service.LibraryService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookstore/library")
public class LibraryController {
	
	@Autowired
	private LibraryService service;

	@PostMapping("/save")
	public ResponseEntity<String> addToLibrary(@RequestBody Library library) throws EmptyInputException {
		service.addBookToLibrary(library);
		return new ResponseEntity<String>("Book added to your library", HttpStatus.OK);
	}

	@DeleteMapping("/remove/{username}/{bookid}")
	public ResponseEntity<String> removeBookFromLibrary(@PathVariable("username") String username, @PathVariable("bookid") String bookid) throws BookNotFoundException {
		service.removeBookFromLibrary(username, bookid);
		return new ResponseEntity<String>("Book removed from " + username + "'s library, bookId: " + bookid, HttpStatus.OK);
	}

	@GetMapping("/show/{username}")
	public ResponseEntity<List<Library>> showBooks(@PathVariable("username") String username) throws UserNotFoundException {
		return new ResponseEntity<List<Library>>(service.displayAllBooksInLibrary(username), HttpStatus.OK);
	}
	
	@GetMapping("/show/{username}/{bookid}")
	public ResponseEntity<Optional<Library>> showSpecificBook(@PathVariable("username") String username, @PathVariable("bookid") String bookid) throws UserNotFoundException {
		return new ResponseEntity<Optional<Library>>(service.getSpecificBook(username, bookid), HttpStatus.OK);
	}

	@GetMapping("/show/{username}/content/{bookid}")
	public ResponseEntity<Optional<BookContents>> displayBookContents(@PathVariable("username") String username, @PathVariable("bookid") String bookid) throws LibraryEmptyException {
		return new ResponseEntity<Optional<BookContents>>(service.displayBookContents(bookid), HttpStatus.OK);
	}
	
	@GetMapping("/check/{username}/{bookid}")
	public ResponseEntity<String> checkLibrary(@PathVariable("username") String username, @PathVariable("bookid") String bookid) {
		return new ResponseEntity<String>(service.checkLibrary(username, bookid), HttpStatus.OK);
	}

}
