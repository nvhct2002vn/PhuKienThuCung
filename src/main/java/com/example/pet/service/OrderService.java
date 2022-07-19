package com.example.pet.service;

import java.util.List;

import com.example.pet.entities.Order;
import com.example.pet.entities.OrderDetail;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order save(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByAll();

	List<Order> findByUsername(int user_id);

}
