package com.example.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pet.entities.Order;
import com.example.pet.entities.OrderDetail;
import com.example.pet.service.OrderDetailService;
import com.example.pet.service.OrderService;

@Controller
public class OrderDetailController {

	@Autowired
	OrderDetailService detailService;

	@Autowired
	OrderService orderService;
	
	@GetMapping("/order/detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> listOrderDetails = this.detailService.findByOrder(id);
		model.addAttribute("listOrderDetails", listOrderDetails);
		
		Order order = this.orderService.findById(id);
		model.addAttribute("order", order);
		
		return "/views/order/OrderDetail";
	}
}
