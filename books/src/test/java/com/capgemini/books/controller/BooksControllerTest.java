package com.capgemini.books.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.books.model.Books;
import com.capgemini.books.service.BooksService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BooksService service;

	private Books book;
	private List<Books> list;

	@BeforeEach
	public void setup() throws Exception {
		book = new Books("1","name","author","publisher","genre","desc",399,2020,"image",36.99);
	}

	@Test
	void addBookTest() throws Exception {	
		when(service.addBooks(any())).thenReturn(book);
		mockMvc.perform(post("/bookstore/books/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(book)))
		.andExpect(status().isOk());
	}

	@Test
	public void getAllBookTest() throws Exception{
		when(service.getAllBooks()).thenReturn(list);
		mockMvc.perform(get("/bookstore/books/get")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(book)))
		.andExpect(status().isOk());
	}

	@Test
	public void getBookByIdTest() throws Exception{
		when(service.getBookById(any())).thenReturn(Optional.of(book));
		mockMvc.perform(get("/bookstore/books/get/bookid")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void getBookByNameTest() throws Exception{
		List<Books> list2 = new ArrayList<>();
		list2.add(new Books("1","name","author","publisher","genre","desc",399,2020,"image",98.99));
		list2.add(new Books("2","name2","author2","publisher2","genre","desc2",499,1020,"image",98.77));
		list2.add(new Books("3","name3","author3","publisher3","genre","desc3",699,3020,"image",54.88));
		when(service.getBookByName("name")).thenReturn(Optional.of(list2));
		mockMvc.perform(get("/bookstore/books/get/name/name")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void getBookByGenreTest() throws Exception{
		List<Books> list2 = new ArrayList<>();
		list2.add(new Books("1","name","author","publisher","genre","desc",399,2020,"image",98.99));
		list2.add(new Books("2","name2","author2","publisher2","genre","desc2",499,1020,"image",98.77));
		list2.add(new Books("3","name3","author3","publisher3","genre","desc3",699,3020,"image",54.88));
		when(service.getBooksByGenre("genre")).thenReturn(Optional.of(list2));
		mockMvc.perform(get("/bookstore/books/get/genre/genre")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void deleteBookTest() throws Exception{
		//		when(service.deleteBooks(any())).thenReturn(Optional.of(book));
		mockMvc.perform(delete("/bookstore/books/delete/bookid")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void updateBookTest() throws Exception{
		when(service.updateBooks("1", book)).thenReturn(book);
		mockMvc.perform(put("/bookstore/books/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(book)))
		.andExpect(status().isOk());
	}

	public String asJsonString(Books obj) throws Exception{
		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(obj);
			return json;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
