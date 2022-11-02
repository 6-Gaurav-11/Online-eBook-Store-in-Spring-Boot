package com.capgemini.library.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.library.model.BookContents;

@Component
@FeignClient(name="bookcontents", url="localhost:8500")
public interface BookContentsProxy {
	
	@GetMapping("/bookstore/content/get/{bookid}")
	public Optional<BookContents> getBookById(@PathVariable("bookid") String bookid);

}