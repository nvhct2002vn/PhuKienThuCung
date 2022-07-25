package com.example.pet.controller.admin;

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
@RequestMapping("/admin")
public class OrderControllerAdmin {

	@Autowired
	OrderService orderService;

	@RequestMapping("/order")
	public String listOrder(Model model) {
		List<Order> listOrders = this.orderService.findByAll();
		
		model.addAttribute("listOrders", listOrders);
		
		return "/views/admin/order/listOrder";
	}

	@RequestMapping("/order/detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Integer id) {
		Order order = this.orderService.findById(id);
		model.addAttribute("order", order);

		return "/views/admin/order/OrderDetail";
	}
}
