package com.example.pet.service;

import java.util.List;

import com.example.pet.entities.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> findByOrder(Integer id);

}
