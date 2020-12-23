package com.mhtech;

import com.mhtech.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboProjectConsumerApplicationTests {

	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads() {

		orderService.findOrdersByUsername("张三");

	}

}
