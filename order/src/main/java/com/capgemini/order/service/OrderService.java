package com.capgemini.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.order.model.Books;
import com.capgemini.order.model.Orders;
import com.capgemini.order.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BookDetailsProxy proxy;
	
	public Orders addOrder(Orders orders) {
		Orders newOrder = new Orders();
		Books b = new Books();
		b = proxy.getBookByBookId(orders.getBookId());
//		newOrder.setOrderId(UUID.randomUUID());
		newOrder.setBookId(orders.getBookId());
		newOrder.setUsername(orders.getUsername());
		newOrder.setAuthor(b.getAuthor());
		newOrder.setDescription(b.getDescription());
		newOrder.setGenre(b.getGenre());
		newOrder.setImageUrl(b.getImageUrl());
		newOrder.setName(b.getName());
		newOrder.setPages(b.getPages());
		newOrder.setPrice(b.getPrice());
		newOrder.setPublisher(b.getPublisher());
		newOrder.setPublishYear(b.getPublishYear());
		repo.save(newOrder);
		return newOrder;
	}
	
	public List<Orders> displayAllOrders() {
		List<Orders> ord = repo.findAll();
		return ord;
	}
	
	public List<Orders> orderByUsername(String username) {
		List<Orders> ord = repo.findByUsername(username);
		return ord;
	}
}
