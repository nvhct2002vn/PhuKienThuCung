package com.example.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping("/order/check-out")
	public String checkout() {
		return "/views/order/check-out";
	}
}
