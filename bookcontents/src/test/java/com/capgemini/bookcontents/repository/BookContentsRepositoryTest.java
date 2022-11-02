package com.capgemini.bookcontents.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.bookcontents.model.BookContents;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookContentsRepositoryTest {

	@Mock
	BookContentsRepository repository;

	@Test
	public void getAllTest() {
		List<BookContents> list = (List<BookContents>)Arrays.asList(new BookContents("1","Hello1"), new BookContents("2","Hello2"));
		when(repository.findAll()).thenReturn(list);
		assertEquals(2, repository.findAll().size());	
	}
	
	@Test
	public void findByIdTest() {
		Optional<BookContents> b1 = Optional.of(new BookContents("1","Hello1"));
		when(repository.findById("1")).thenReturn(b1);
		BookContents p = repository.findById("1").get();
		assertEquals("Hello1", p.getSynopsis());
	}
	
	@Test
	public void saveTest() {
		BookContents p = new BookContents("1","Hello1");
		when(repository.save(p)).thenReturn(p);
		assertEquals(p, repository.save(p));
	}
	
	@Test
	public void deleteByIdTest() {
		@SuppressWarnings("unused")
		BookContents p = new BookContents("1","Hello1");
		repository.deleteById("1");
		verify(repository,times(1)).deleteById("1");
	}
}	

