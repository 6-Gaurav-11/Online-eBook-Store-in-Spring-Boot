package com.capgemini.library.repository;

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

import com.capgemini.library.model.Library;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class LibraryRepositoryTest {

	@Mock
	LibraryRepository repository;

	@Test
	public void getAllTest() {
		List<Library> list = (List<Library>)Arrays.asList(new Library("1","ranked1","A","B","C","D","E","Z", 12,2008,"F",9), new Library("2","ranked2","A","B","C","D","E","Z", 88,2009,"F",7));
		when(repository.findAll()).thenReturn(list);
		assertEquals(2, repository.findAll().size());	
	}
	
	@Test
	public void findByIdTest() {
		Optional<Library> b1 = Optional.of(new Library("1","ranked1","A","B","C","D","E","Z", 12,2008,"F",9));
		when(repository.findByBookId("1")).thenReturn(b1);
		Library p = repository.findByBookId("1").get();
		assertEquals(2008, p.getPublishYear());
		assertEquals("B",p.getName());
	}
	
	@Test
	public void saveTest() {
		Library p = new Library("1","ranked1","A","B","C","D","E","Z", 12,2008,"F",9);
		when(repository.save(p)).thenReturn(p);
		assertEquals(p, repository.save(p));
	}
	
	@Test
	public void deleteByUsernameIdTest() {
		@SuppressWarnings("unused")
		Library p = new Library("1","ranked1","A","B","C","D","E","Z", 12,2008,"F",9);
		repository.deleteByUsernameAndBookId("ranked1", "1");
		verify(repository,times(1)).deleteByUsernameAndBookId("ranked1","1");
	}
}	
