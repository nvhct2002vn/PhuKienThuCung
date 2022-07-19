package com.example.pet.service.iplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.entities.OrderDetail;
import com.example.pet.repository.OrderDetailRepository;
import com.example.pet.service.OrderDetailService;

@Service
public class OrderDetailServiceIplm implements OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> findByOrder(Integer id) {
		return this.orderDetailRepository.findByOrder(id);
	}
}
