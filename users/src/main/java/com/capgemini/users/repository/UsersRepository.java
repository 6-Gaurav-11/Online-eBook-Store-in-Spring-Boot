package com.capgemini.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.users.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	

}
