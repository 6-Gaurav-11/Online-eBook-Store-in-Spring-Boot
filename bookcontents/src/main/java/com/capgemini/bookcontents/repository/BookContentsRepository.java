package com.capgemini.bookcontents.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.bookcontents.model.BookContents;

public interface BookContentsRepository extends MongoRepository<BookContents, String>{

}
