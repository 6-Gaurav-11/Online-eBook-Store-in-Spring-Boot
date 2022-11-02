package com.capgemini.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.order.model.Orders;

public interface OrderRepository extends MongoRepository<Orders, String>{
	
	@Query("{username :?0}")
	public List<Orders> findByUsername(String username);
	
}
