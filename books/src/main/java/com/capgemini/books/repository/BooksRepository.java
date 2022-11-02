package com.capgemini.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.books.model.Books;

public interface BooksRepository extends MongoRepository<Books, String>{
	
	@Query("{name :?0}")
    public Optional<Books> findByName(String name);
	
	@Query("{bookId :?0}")
    public Optional<Books> findByBookId(String bookId);
	
	@Query("{genre :?0}")
	public Optional<List<Books>> findByGenre(String genre);
	
	@Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
	public Optional<List<Books>> findByNameLike(String name);
	
}
