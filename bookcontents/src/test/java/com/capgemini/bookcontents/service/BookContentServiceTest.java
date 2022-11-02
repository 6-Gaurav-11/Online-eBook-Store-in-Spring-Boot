package com.capgemini.bookcontents.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.bookcontents.exception.BookNotFoundException;
import com.capgemini.bookcontents.exception.NoContentEntryException;
import com.capgemini.bookcontents.model.BookContents;
import com.capgemini.bookcontents.repository.BookContentsRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookContentServiceTest {

	@InjectMocks
	private BookContentService service;
	
	@Mock
	private BookContentsRepository repository;
	
	@Test
	public void testGetById() throws BookNotFoundException {
		BookContents b = new BookContents("1","lorem ipsum");
		when(repository.findById("1")).thenReturn(Optional.of(b));
		BookContents savedcontent = service.getBookContentByBookId(b.getBookId()).get();
		assertThat(savedcontent).isNotNull();
	}
	
	@Test
	public void testAdd() throws NoContentEntryException {
		BookContents b = new BookContents("1","lorem ipsum");
		when(repository.save(b)).thenReturn(b);
		assertEquals(b, service.addBookContent(b));
	}
	
	@Test
	public void testDelete() {
		@SuppressWarnings("unused")
		BookContents b = new BookContents("1","lorem ipsum");
		repository.deleteById("1");
		verify(repository,times(1)).deleteById("1");
	}

}
