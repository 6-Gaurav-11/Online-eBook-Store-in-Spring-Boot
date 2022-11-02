package com.capgemini.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.books.exception.BookNotAvailableException;
import com.capgemini.books.exception.EmptyInputException;
import com.capgemini.books.exception.NoBooksAvailableException;
import com.capgemini.books.model.Books;
import com.capgemini.books.repository.BooksRepository;

@Service
public class BooksService{

	@Autowired
	private BooksRepository repo;
	
	//adding books
	public Books addBooks(Books book) throws EmptyInputException {
		if(book.getName()==null || book.getBookId()==null) 
			throw new EmptyInputException("Input fields are empty.");
		else {
			Books b1 = repo.save(book);
			return b1;
		}
		
	}

	//getting books by id
	public Optional<Books> getBookById(String bookId) throws BookNotAvailableException {
		Optional<Books> b2 = repo.findById(bookId);
		if(b2.isEmpty())
			throw new BookNotAvailableException();
		else
			return repo.findById(bookId);
	}

	//getting all books
	public List<Books> getAllBooks() throws NoBooksAvailableException {
		List<Books> b3 = repo.findAll();
		if(b3.isEmpty())
			throw new NoBooksAvailableException();
		else
			return b3;
	}


	//deleting books
	public void deleteBooks(String id) throws BookNotAvailableException {
		if(repo.findById(id).isPresent())
			repo.deleteById(id);
		else
			throw new BookNotAvailableException();
		
	}

	//updating books
	public Books updateBooks(String bookId, Books book) throws BookNotAvailableException {
		Optional<Books> findBook = repo.findById(bookId);
		if(findBook.isPresent()) {
			Books b4 = findBook.get();
			b4.setAuthor(book.getAuthor());
			b4.setDescription(book.getDescription());
			b4.setGenre(book.getGenre());
			b4.setName(book.getName());
			b4.setPages(book.getPages());
			b4.setPublisher(book.getPublisher());
			b4.setPublishYear(book.getPublishYear());
			b4.setImageUrl(book.getImageUrl());
			b4.setPrice(book.getPrice());
			
			return repo.save(b4);
		}
		else
			throw new BookNotAvailableException();
	}

	//getting books by name
	public Optional<List<Books>> getBookByName(String name) throws BookNotAvailableException {
		Optional<List<Books>> b5 = repo.findByNameLike(name);
		if(b5.isEmpty())
			throw new BookNotAvailableException();
		else
			return repo.findByNameLike(name);
	}

	//getting books by genre
	public Optional<List<Books>> getBooksByGenre(String genre) throws BookNotAvailableException {
		Optional<List<Books>> b6 = repo.findByGenre(genre);
		if(b6.isEmpty())
			throw new BookNotAvailableException();
		else
			return b6;
	}


}
