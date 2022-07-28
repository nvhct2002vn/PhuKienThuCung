package com.example.pet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Order;
import com.example.pet.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/orders")
public class OrderRestController {

	@Autowired
	OrderService orderService;

	@GetMapping("{id}")
	public Order findById(@PathVariable("id") Integer id) {
		return this.orderService.findById(id);
	}

	@PostMapping("")
	public Order post(@RequestBody JsonNode orderData) {
		return this.orderService.save(orderData);
	}

	@PutMapping("{id}")
	public Order update(@RequestBody Order orderData) {
		return this.orderService.update(orderData);
	}

}
