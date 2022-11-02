package com.capgemini.books.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.books.exception.BookNotAvailableException;
import com.capgemini.books.exception.EmptyInputException;
import com.capgemini.books.exception.NoBooksAvailableException;
import com.capgemini.books.model.Books;
import com.capgemini.books.repository.BooksRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BooksServiceTest {

	@InjectMocks
	private BooksService service;

	@Mock
	private BooksRepository repository;

	Books book;

	@BeforeEach
	public void setup() {
		book = new Books("1","name","author","publisher","genre","desc",399,2020,"image",77);
	}

	@Test
	public void testGetAll() throws NoBooksAvailableException {
		Books book2 = new Books("2","name2","author2","publisher2","genre2","desc2",599,2021,"image2",77);
		when(repository.findAll()).thenReturn(List.of(book, book2));
		List<Books> list = service.getAllBooks();
		assertThat(list).isNotNull();
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	public void testGetById() throws BookNotAvailableException {
		when(repository.findById("1")).thenReturn(Optional.of(book));
		Books savedbook = service.getBookById(book.getBookId()).get();
		assertThat(savedbook).isNotNull();
	}

	@Test
	public void testSave() throws EmptyInputException {
		Books book2 = new Books("2","name2","author2","publisher2","genre2","desc2",599,2021,"image2",77);
		when(repository.save(book2)).thenReturn(book2);
		assertEquals(book2, service.addBooks(book2));
	}

	@Test
	public void testDelete() throws BookNotAvailableException {
		Books book2 = new Books("2","name2","author2","publisher2","genre2","desc2",599,2021,"image2",77);
		repository.save(book2);
		repository.deleteById("2");
		verify(repository,times(1)).deleteById("2");
	}

}
