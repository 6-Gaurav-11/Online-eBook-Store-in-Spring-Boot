package com.capgemini.users.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.capgemini.users.model.Users;
import com.capgemini.users.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UsersService service;
	
	private Users user;
	private List<Users> list;
	
	@BeforeEach
	public void setup() throws Exception {
		user = new Users("username","fullname","email@email.com","password");
	}
	
	@Test
	void addUserTest() throws Exception {	
		when(service.addUser(any())).thenReturn(user);
		mockMvc.perform(post("/bookstore/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)))
				.andExpect(status().isOk());
	}
	
	@Test
    public void getAllUsersTest() throws Exception{
        when(service.getAllUsers()).thenReturn(list);
        mockMvc.perform(get("/bookstore/users/getall")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
	
	@Test
    public void getUserByIdTest() throws Exception{
        when(service.getUserByUsername(any())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/bookstore/users/get/username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
	
	@Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/bookstore/users/delete/username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
	
	@Test
    public void updateUserTest() throws Exception{
        when(service.updateUser("username", user)).thenReturn(user);
        mockMvc.perform(put("/bookstore/users/update/username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
	
	
	public String asJsonString(Users obj) throws Exception{
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
