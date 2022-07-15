package com.example.pet.service.iplm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.repository.OrderDetailRepository;
import com.example.pet.service.OrderDetailService;

@Service
public class OrderDetailServiceIplm implements OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;
}
