package com.capgemini.bookcontents.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.bookcontents.model.BookContents;
import com.capgemini.bookcontents.service.BookContentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookContentController.class)
class BookContentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookContentService service;

	private BookContents contents;

	@BeforeEach
	public void setup() throws Exception {
		contents = new BookContents("1","the book contents");
	}
	
	@Test
	void addBookContentTest() throws Exception {	
		when(service.addBookContent(any())).thenReturn(contents);
		mockMvc.perform(post("/bookstore/content/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(contents)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getBookContentByIdTest() throws Exception{
		when(service.getBookContentByBookId(any())).thenReturn(Optional.of(contents));
		mockMvc.perform(get("/bookstore/content/get/bookid")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteContentTest() throws Exception {
		mockMvc.perform(delete("/bookstore/content/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(contents)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateContentTest() throws Exception {
		when(service.updateContent("1", contents)).thenReturn(contents);
		mockMvc.perform(put("/bookstore/content/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(contents)))
				.andExpect(status().isOk());
	}
	
	public String asJsonString(BookContents obj) throws Exception{
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
