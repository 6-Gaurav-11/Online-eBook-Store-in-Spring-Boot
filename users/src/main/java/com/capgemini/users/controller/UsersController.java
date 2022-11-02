package com.capgemini.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.users.exception.EmptyInputException;
import com.capgemini.users.exception.NoUsersAvailableException;
import com.capgemini.users.exception.UserNotFoundException;
import com.capgemini.users.model.Users;
import com.capgemini.users.service.UsersService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookstore/users")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@PostMapping("/add")
	public ResponseEntity<Users> addUser(@RequestBody Users user) throws EmptyInputException {
		return new ResponseEntity<Users>(service.addUser(user), HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addBooks(book));
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Users>> getAllUsers() throws NoUsersAvailableException {
		return new ResponseEntity<List<Users>>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{username}")
	public ResponseEntity<Optional<Users>> getUserById(@PathVariable("username") String username) throws UserNotFoundException {
		return new ResponseEntity<Optional<Users>>(service.getUserByUsername(username), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable("username") String username) throws UserNotFoundException {
		service.deleteUser(username);
		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}
	
	@PutMapping("/update/{username}")
	public ResponseEntity<String> updateUser(@PathVariable("username") String username,@RequestBody Users user) throws UserNotFoundException {
		service.updateUser(username, user);
		return new ResponseEntity<String>("User Updated Successfully", HttpStatus.OK);
	}

}
