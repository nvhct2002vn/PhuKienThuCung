package com.example.pet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.entities.Account;
import com.example.pet.entities.Order;
import com.example.pet.service.AccountService;
import com.example.pet.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	AccountService accountService;

	@RequestMapping("/check-out")
	public String checkout() {
		return "/views/order/check-out";
	}

	@GetMapping("/list")
	public String listOrder(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		Account acc = this.accountService.findByUsername(username);
		int user_id = acc.getId();
		List<Order> listOrders = this.orderService.findByUsername(user_id);
		model.addAttribute("listOrders", listOrders);

		return "/views/order/listOrder";
	}

	@GetMapping("/detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Integer id) {
		Order order = this.orderService.findById(id);
		model.addAttribute("order", order);

		return "/views/order/OrderDetail";
	}
}
