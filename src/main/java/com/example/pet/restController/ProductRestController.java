package com.example.pet.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Category;
import com.example.pet.entities.Product;
import com.example.pet.service.ProductService;

@RestController
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping("/api/product")
	public List<Product> get() {
		return this.productService.findAll();
	}

	@GetMapping("/api/product/{id}")
	public Product getById(@PathVariable("id") Integer id) {
		return this.productService.findById(id);
	}

	@PostMapping("/api/product")
	public Product post(@RequestBody Category category) {
		return this.productService.save(category);
	}
}
