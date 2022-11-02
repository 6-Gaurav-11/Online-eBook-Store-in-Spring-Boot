package com.capgemini.users.service;

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

import com.capgemini.users.exception.EmptyInputException;
import com.capgemini.users.exception.NoUsersAvailableException;
import com.capgemini.users.exception.UserNotFoundException;
import com.capgemini.users.model.Users;
import com.capgemini.users.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UsersServiceTest {

	@InjectMocks
	private UsersService service;
	
	@Mock
	private UsersRepository repository;
	
	private Users user;
	
	@BeforeEach
	public void setup() {
		user = new Users("abc","ABC","abc@abc.com","123");
	}
	
	@Test
	public void testGetAll() throws NoUsersAvailableException {
		Users user2 = new Users("xyz","XYZ","xyz@xyz.com","456");
		when(repository.findAll()).thenReturn(List.of(user, user2));
		List<Users> list = service.getAllUsers();
		assertThat(list).isNotNull();
		assertThat(list.size()).isEqualTo(2);
	}
	
	@Test
	public void testGetById() throws UserNotFoundException {
		when(repository.findById("abc")).thenReturn(Optional.of(user));
		Users savedUser = service.getUserByUsername(user.getUsername()).get();
		assertThat(savedUser).isNotNull();
	}
	
	@Test
	public void testSave() throws EmptyInputException {
		Users p = new Users("abc","ABC","abc@abc.com","123");
		when(repository.save(p)).thenReturn(p);
		assertEquals(p, service.addUser(p));
	}
	
	@Test
	public void testDelete() throws UserNotFoundException {
		@SuppressWarnings("unused")
		Users p = new Users("abc","ABC","abc@abc.com","123");
		repository.deleteById("abc");
		verify(repository,times(1)).deleteById("abc");
	}
		
}
