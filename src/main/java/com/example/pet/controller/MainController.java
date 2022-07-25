package com.example.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("")
	public String home() {
		return "/views/main/home";
	}
	@RequestMapping("/login-form")
	public String login() {
		return "/views/main/login";
	}
}
