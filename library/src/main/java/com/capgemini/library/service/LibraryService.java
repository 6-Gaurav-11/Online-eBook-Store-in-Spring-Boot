package com.capgemini.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.library.exception.BookNotFoundException;
import com.capgemini.library.exception.EmptyInputException;
import com.capgemini.library.exception.LibraryEmptyException;
import com.capgemini.library.exception.UserNotFoundException;
import com.capgemini.library.model.BookContents;
import com.capgemini.library.model.Books;
import com.capgemini.library.model.Library;
import com.capgemini.library.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository repo;

	@Autowired
	public BookDetailsProxy bookproxy;

	@Autowired
	public BookContentsProxy contentproxy;

	public Library addBookToLibrary(Library library) throws EmptyInputException {
		if(library.getBookId()==null || library.getUsername()==null) {
			throw new EmptyInputException();
		}
		else {
			Library newlib = new Library();
			Books b = new Books();
			b = bookproxy.getBookByBookId(library.getBookId());
			newlib.setId(library.getUsername()+library.getBookId());
			newlib.setBookId(library.getBookId());
			newlib.setUsername(library.getUsername());
			newlib.setAuthor(b.getAuthor());
			newlib.setGenre(b.getGenre());
			newlib.setDescription(b.getDescription());
			newlib.setImageUrl(b.getImageUrl());
			newlib.setName(b.getName());
			newlib.setPublisher(b.getPublisher());
			newlib.setPages(b.getPages());
			newlib.setPublishYear(b.getPublishYear());
			newlib.setPrice(b.getPrice());
			repo.save(newlib);
			return newlib;
		}
	}

	public Optional<Library> getSpecificBook(String username, String bookId) {
		return repo.findById(username+bookId);
	}

	public void removeBookFromLibrary(String username, String bookId) throws BookNotFoundException {
		if(repo.findById(username+bookId).isPresent())
			repo.deleteById(username+bookId);
		else
			throw new BookNotFoundException();
	}

	public List<Library> displayAllBooksInLibrary(String username) throws UserNotFoundException {
		List<Library> lib = repo.findByUsername(username);
		return lib;
	}


public Optional<BookContents> displayBookContents(String bookid) throws LibraryEmptyException {
	if(contentproxy.getBookById(bookid).isEmpty())
		throw new LibraryEmptyException();
	else {
		Optional<BookContents> cont = contentproxy.getBookById(bookid);
		return cont;
	}
}

public String checkLibrary(String username, String bookId) {
	if(repo.existsById(username + bookId)) {
		return "Exists";
	}
	else
		return "Not";
}
}
