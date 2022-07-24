package com.example.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.entities.Category;
import com.example.pet.repository.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping("/admin/category")
	public String admin() {
		return "/views/admin/category";
	}
	
	@RequestMapping("/category/{id}")
	public String cateDetail(Model model ,@PathVariable("id") Integer id) {
		Category category = this.categoryRepository.findById(id).get();
		model.addAttribute("category", category);
		return "/views/main/categoryDetail";
	}
}
