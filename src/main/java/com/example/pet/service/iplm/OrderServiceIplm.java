package com.example.pet.service.iplm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.repository.OrderRepository;
import com.example.pet.service.OrderService;

@Service
public class OrderServiceIplm implements OrderService {
	@Autowired
	OrderRepository orderRepository;
}
