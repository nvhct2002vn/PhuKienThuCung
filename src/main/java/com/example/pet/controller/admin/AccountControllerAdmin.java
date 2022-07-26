package com.example.pet.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.entities.Category;
import com.example.pet.repository.CategoryRepository;
import com.example.pet.service.AccountService;

@Controller
public class AccountControllerAdmin {
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/admin/authority")
	public String admin() {
		return "/views/admin/authority";
	}
}
