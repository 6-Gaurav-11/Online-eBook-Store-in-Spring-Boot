package com.capgemini.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.order.model.Orders;
import com.capgemini.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService service;

	@Test
	public void addOrderTest() throws Exception {
		Orders order = new Orders("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		when(service.addOrder(any())).thenReturn(order);
		mockMvc.perform(post("/bookstore/orders/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(order)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getAllOrdersTest() throws Exception {
		Orders order = new Orders("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		List<Orders> list = new ArrayList<Orders>();
		when(service.displayAllOrders()).thenReturn(list);
		mockMvc.perform(get("/bookstore/orders/getall")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(order)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getUserOrdersTest() throws Exception {
		Orders order = new Orders("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		List<Orders> list = new ArrayList<>();
		when(service.orderByUsername(any())).thenReturn(list);
		mockMvc.perform(get("/bookstore/orders/get/apple")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(order)))
				.andExpect(status().isOk());
			
	}

	public String asJsonString(Orders obj) throws Exception{
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
