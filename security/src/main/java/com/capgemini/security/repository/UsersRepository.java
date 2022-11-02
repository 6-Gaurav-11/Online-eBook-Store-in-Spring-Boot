package com.capgemini.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.security.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
//	  Optional<Users> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	}
