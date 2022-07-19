package com.example.pet.service.iplm;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.entities.Order;
import com.example.pet.entities.OrderDetail;
import com.example.pet.repository.OrderDetailRepository;
import com.example.pet.repository.OrderRepository;
import com.example.pet.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceIplm implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public Order save(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Order order = mapper.convertValue(orderData, Order.class);
		orderRepository.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetail"), type)
			.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailRepository.saveAll(details);

		return order;
	}

	@Override
	public Order findById(Integer id) {
		return this.orderRepository.findById(id).get();
	}

	@Override
	public List<Order> findByAll() {
		return this.orderRepository.findAll();
	}

	@Override
	public List<Order> findByUsername(int user_id) {
		return this.orderRepository.findByUserId(user_id);
	}

}
