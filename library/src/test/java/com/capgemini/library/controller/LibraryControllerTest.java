package com.capgemini.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.library.model.BookContents;
import com.capgemini.library.model.Library;
import com.capgemini.library.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LibraryService service;
	
	private Library library;
	private BookContents contents;
	private List<Library> list;
	
	@BeforeEach
	public void setup() throws Exception {
		contents = new BookContents("6","hdiouahdawoudhuawodowuahdo");
		library = new Library("6","apple","1","A","B","C","D","E",659,2022,"F",9);
	}
	
	@Test
	void addLibraryTest() throws Exception {	
		when(service.addBookToLibrary(any())).thenReturn(library);
		mockMvc.perform(post("/bookstore/library/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(library)))
				.andExpect(status().isOk());
	}
	
	@Test
    public void getLibraryTest() throws Exception{
        when(service.displayAllBooksInLibrary(any())).thenReturn(list);
        mockMvc.perform(get("/bookstore/library/show/apple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(library)))
                .andExpect(status().isOk());
    }
	
	@Test
    public void removeLibraryTest() throws Exception{
//        when(service.removeBookFromLibrary(any(), any())).thenReturn("Deleted");
        mockMvc.perform(delete("/bookstore/library/remove/username/bookid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    public void bookContentTest() throws Exception{
		when(service.displayBookContents(any())).thenReturn(Optional.of(contents));
        mockMvc.perform(get("/bookstore/library/show/username/content/bookid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contents)))
                .andExpect(status().isOk());
    }
	
	public String asJsonString(Library obj) throws Exception{
        try {
        	ObjectMapper om = new ObjectMapper();
        	String json = om.writeValueAsString(obj);
            return json;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
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
