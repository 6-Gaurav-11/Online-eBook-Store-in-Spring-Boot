package com.capgemini.order.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.order.model.Orders;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class OrderRepositoryTest {

	@Mock
	OrderRepository repository;
	
	@Test
	public void getAllOrderTest() {
		List<Orders> list = (List<Orders>)Arrays.asList(new Orders("1","ranked1","A","B","C","D","E","Z", 12,2008,"F",9), new Orders("2","ranked2","A","B","C","D","E","Z", 88,2009,"F",7));
		when(repository.findAll()).thenReturn(list);
		assertEquals(2, repository.findAll().size());
	}
	
	@Test
	public void getUserOrdersTest() {
		List<Orders> list = (List<Orders>)Arrays.asList(new Orders("1","rank1","A","B","C","D","E","Z", 12,2008,"F",9), new Orders("2","rank1","A","B","C","D","E","Z", 88,2009,"F",7));
		when(repository.findByUsername("rank1")).thenReturn(list);
		assertEquals(2, repository.findByUsername("rank1").size());
	}
	
	@Test
	public void saveOrder() {
		Orders o1 = new Orders("1","rank1","A","B","C","D","E","Z", 12,2008,"F",9);
		when(repository.save(o1)).thenReturn(o1);
		assertEquals(o1, repository.save(o1));
	}
	

}
