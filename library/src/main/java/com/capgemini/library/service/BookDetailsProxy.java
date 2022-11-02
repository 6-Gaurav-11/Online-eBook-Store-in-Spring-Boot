package com.capgemini.library.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.library.model.Books;

@Component
@FeignClient(name="bookservice", url="localhost:8100")
public interface BookDetailsProxy {
	
	@GetMapping("/bookstore/books/get/{bookid}")
	public Books getBookByBookId(@PathVariable String bookid);

}
