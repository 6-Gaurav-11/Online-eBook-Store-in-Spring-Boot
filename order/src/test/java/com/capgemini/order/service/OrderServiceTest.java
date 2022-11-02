package com.capgemini.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.order.model.Orders;
import com.capgemini.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

	@Mock
	private OrderRepository repository;
	
	@InjectMocks
	private OrderService service;
	
	@Test
	public void addOrderTest() {
		Orders o = new Orders("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		when(repository.save(o)).thenReturn(o);
		assertEquals(o, repository.save(o));
	}
	
	@Test
	public void getOrderTest() {
		Orders o1 = new Orders("6","apple","1","A","B","C","D","E",659,2022,"F",9);
		Orders o2 = new Orders("33","apple","2","A","B","C","D","E",659,2022,"F",8);
		repository.save(o1);
		repository.save(o2);
		when(repository.findAll()).thenReturn(List.of(o1,o2));
		List<Orders> list = service.displayAllOrders();
		assertThat(list).isNotNull();
		assertThat(list.size()).isEqualTo(2);
	}

}
