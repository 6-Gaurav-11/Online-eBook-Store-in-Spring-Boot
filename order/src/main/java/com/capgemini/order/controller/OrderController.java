package com.capgemini.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.order.model.Orders;
import com.capgemini.order.service.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookstore/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping("/add")
	public ResponseEntity<String> addOrder(@RequestBody Orders orders) {
		service.addOrder(orders);
		return new ResponseEntity<String>("Order placed", HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Orders>> getAllOrders() {
		return new ResponseEntity<List<Orders>>(service.displayAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{username}")
	public ResponseEntity<List<Orders>> getOrder(@PathVariable("username") String username) {
		return new ResponseEntity<List<Orders>>(service.orderByUsername(username), HttpStatus.OK);
	}

}
