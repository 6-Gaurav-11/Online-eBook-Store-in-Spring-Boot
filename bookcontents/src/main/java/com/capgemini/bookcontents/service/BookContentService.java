package com.capgemini.bookcontents.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bookcontents.exception.BookNotFoundException;
import com.capgemini.bookcontents.exception.NoContentEntryException;
import com.capgemini.bookcontents.model.BookContents;
import com.capgemini.bookcontents.repository.BookContentsRepository;

@Service
public class BookContentService {

	@Autowired
	private BookContentsRepository repo;

	public Optional<BookContents> getBookContentByBookId(String bookId) throws BookNotFoundException {
		if(repo.findById(bookId).isPresent())
			return repo.findById(bookId);
		else
			throw new BookNotFoundException();
	}

	public BookContents addBookContent(BookContents cont) throws NoContentEntryException {
		if(cont.getBookId()==null || cont.getSynopsis()==null)
			throw new NoContentEntryException();
		else
			return repo.save(cont);
	}

	public BookContents updateContent(String bookId, BookContents cont) {
		Optional<BookContents> findContent = repo.findById(bookId);
		BookContents b = findContent.get();
		b.setBookId(cont.getBookId());
		b.setSynopsis(cont.getSynopsis());
		return repo.save(b);
	}

	public void deleteContent(String bookId) {
		repo.deleteById(bookId);
	}

}
