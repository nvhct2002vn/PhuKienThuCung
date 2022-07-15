package com.example.pet.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Category;
import com.example.pet.service.CategoryService;

@RestController
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/api/category")
	public List<Category> get() {
		return this.categoryService.findAll();
	}

	@GetMapping("/api/category/{id}")
	public Category getById(@PathVariable("id") Integer id) {
		return this.categoryService.findById(id);
	}

	@PostMapping("/api/category")
	public Category post(@RequestBody Category category) {
		return this.categoryService.save(category);
	}

	@PutMapping("/api/category/{id}")
	public Category put(@PathVariable("id") Integer id, @RequestBody Category category) {
		return this.categoryService.save(category);
	}
}
