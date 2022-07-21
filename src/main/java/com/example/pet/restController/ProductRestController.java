package com.example.pet.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Category;
import com.example.pet.entities.Product;
import com.example.pet.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping("")
	public List<Product> get() {
		return this.productService.findAll();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable("id") Integer id) {
		return this.productService.findById(id);
	}

	@PostMapping("")
	public Product post(@RequestBody Product product) {
		return this.productService.save(product);
	}

	@PutMapping("/{id}")
	public Product update(@RequestBody Product product) {
		return this.productService.update(product);
	}
}
