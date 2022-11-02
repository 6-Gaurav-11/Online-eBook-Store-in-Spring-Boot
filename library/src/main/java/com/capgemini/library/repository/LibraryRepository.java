package com.capgemini.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.library.model.Library;

public interface LibraryRepository extends MongoRepository<Library, String>{

	@Query("{username :?0}")
    public List<Library> findByUsername(String username);
	
	@Query("{bookId :?0}")
    public Optional<Library> findByBookId(String bookId);
	
	public void deleteByUsernameAndBookId(String username, String bookId);
	
}
