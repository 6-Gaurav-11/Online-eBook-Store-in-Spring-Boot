package com.capgemini.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.library.exception.EmptyInputException;
import com.capgemini.library.exception.UserNotFoundException;
import com.capgemini.library.model.Library;
import com.capgemini.library.repository.LibraryRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LibraryServiceTest {

	@Mock
    private LibraryRepository repository;

    @InjectMocks
    private LibraryService service;
    
	@Test
	public void addTest() throws EmptyInputException {
		Library lib = new Library("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		when(repository.save(lib)).thenReturn(lib);
		assertEquals(lib, repository.save(lib));
	}
	
	@Test
	public void deleteTest() {
		Library lib = new Library("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		repository.save(lib);
		repository.deleteByUsernameAndBookId("apple","1");
		verify(repository,times(1)).deleteByUsernameAndBookId("apple","1");
	}
	
	@Test
	public void displayTest() throws UserNotFoundException {
		Library lib1 = new Library("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		Library lib2 = new Library("33","apple","2","A","B","C","D","E",659,2022,"F",8);
		repository.save(lib1);
		repository.save(lib2);
		when(repository.findByUsername("apple")).thenReturn(List.of(lib1, lib2));
		List<Library> list = service.displayAllBooksInLibrary("apple");
		assertThat(list).isNotNull();
		assertThat(list.size()).isEqualTo(2);
	}

}
