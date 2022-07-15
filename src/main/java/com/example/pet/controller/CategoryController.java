package com.example.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
	@RequestMapping("/admin/category")
	public String admin() {
		return "/views/admin/category";
	}
}
