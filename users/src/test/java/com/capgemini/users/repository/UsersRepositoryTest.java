package com.capgemini.users.repository;

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

import com.capgemini.users.model.Users;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UsersRepositoryTest {

	@Mock
	UsersRepository repository;

	@Test
	public void getAllTest() {
		List<Users> list = (List<Users>)Arrays.asList(new Users("abc","ABC","abc@abc.com","123"), new Users("xyz","XYZ","xyz@xyz.com","456"));
		when(repository.findAll()).thenReturn(list);
		assertEquals(2, repository.findAll().size());	
	}
	
	@Test
	public void findByIdTest() {
		Optional<Users> b1 = Optional.of(new Users("abc","ABC","abc@abc.com","123"));
		when(repository.findById("abc")).thenReturn(b1);
		Users p = repository.findById("abc").get();
		assertEquals("ABC", p.getFullName());
		assertEquals("abc@abc.com",p.getEmail());
	}
	
	@Test
	public void saveTest() {
		Users p = new Users("abc","ABC","abc@abc.com","123");
		when(repository.save(p)).thenReturn(p);
		assertEquals(p, repository.save(p));
	}
	
	@Test
	public void deleteByIdTest() {
		@SuppressWarnings("unused")
		Users p = new Users("abc","ABC","abc@abc.com","123");
		repository.deleteById("abc");
		verify(repository,times(1)).deleteById("abc");
	}
}	
