package com.capgemini.books.repository;

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

import com.capgemini.books.model.Books;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BooksRepositoryTest {

	@Mock
	BooksRepository repository;

	@Test
	public void getAllTest() {
		List<Books> list = (List<Books>)Arrays.asList(new Books("1","A","B","C","D","E",12,2008,"F",8), new Books("2","A","B","C","D","E",88,2009,"F",98));
		when(repository.findAll()).thenReturn(list);
		assertEquals(2, repository.findAll().size());	
	}
	
	@Test
	public void findByIdTest() {
		Optional<Books> b1 = Optional.of(new Books("1","A","B","C","D","E",12,2008,"F",8));
		when(repository.findByBookId("1")).thenReturn(b1);
		Books p = repository.findByBookId("1").get();
		assertEquals(2008, p.getPublishYear());
		assertEquals("A",p.getName());
	}
	
	@Test
	public void saveTest() {
		Books p = new Books("1","A","B","C","D","E",12,2008,"F",8);
		when(repository.save(p)).thenReturn(p);
		assertEquals(p, repository.save(p));
	}
	
	@Test
	public void deleteByIdTest() {
		@SuppressWarnings("unused")
		Books p = new Books("1","A","B","C","D","E",12,2008,"F",8);
		repository.deleteById("1");
		verify(repository,times(1)).deleteById("1");
	}
}	
