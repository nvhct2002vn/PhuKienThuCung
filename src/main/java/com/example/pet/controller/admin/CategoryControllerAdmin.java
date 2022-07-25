package com.example.pet.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.entities.Category;
import com.example.pet.repository.CategoryRepository;

@Controller
public class CategoryControllerAdmin {
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping("/admin/category")
	public String admin() {
		return "/views/admin/category";
	}
}
