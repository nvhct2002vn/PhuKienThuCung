package com.example.pet.service;

import com.example.pet.entities.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order save(JsonNode orderData);

	Order saveOrder(Order order);

}
