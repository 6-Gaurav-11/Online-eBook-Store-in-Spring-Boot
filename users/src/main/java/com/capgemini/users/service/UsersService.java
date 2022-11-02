package com.capgemini.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.users.exception.EmptyInputException;
import com.capgemini.users.exception.NoUsersAvailableException;
import com.capgemini.users.exception.UserNotFoundException;
import com.capgemini.users.model.Users;
import com.capgemini.users.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;

	//add user
	public Users addUser(Users user) throws EmptyInputException {
		if(user.getFullName()==null)
			throw new EmptyInputException();
		else {
			Users u1 = repo.save(user);
			return u1;
		}
	}

	//get all users
	public List<Users> getAllUsers() throws NoUsersAvailableException {
		List<Users> u2 = repo.findAll();
		if(u2.isEmpty())
			throw new NoUsersAvailableException();
		else
			return u2;
	}

	//get user by id
	public Optional<Users> getUserByUsername(String username) throws UserNotFoundException {
		Optional<Users> u3 = repo.findById(username);
		if(u3.isEmpty())
			throw new UserNotFoundException();
		else
			return repo.findById(username);
	}

	//delete user
	public void deleteUser(String username) throws UserNotFoundException {
		if(repo.findById(username).isPresent())
			repo.deleteById(username);
		else
			throw new UserNotFoundException();
		
	}

	public Users updateUser(String username, Users user) throws UserNotFoundException {
		Optional<Users> findUser = repo.findById(username);
		if(findUser.isPresent()) {
			Users u4 = findUser.get();
//			u4.setUsername(user.getUsername());
			u4.setEmail(user.getEmail());
			u4.setFullName(user.getFullName());
			
			String pass = user.getPassword();
			String encryptPass = encoder.encode(pass);
			
			u4.setPassword(encryptPass);
			
			return repo.save(u4);
		}
		else
			throw new UserNotFoundException();
	}

}