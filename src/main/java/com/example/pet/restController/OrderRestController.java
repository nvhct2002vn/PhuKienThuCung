package com.example.pet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Order;
import com.example.pet.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin("*")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/rest/orders")
	public Order post(@RequestBody JsonNode orderData) {
		return this.orderService.save(orderData);
	}
	
	@PostMapping("/rest/order")
	public Order postOrder(@RequestBody Order order) {
		return this.orderService.saveOrder(order);
	}
}
