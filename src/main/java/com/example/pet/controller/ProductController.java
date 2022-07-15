package com.example.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.entities.Product;
import com.example.pet.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping("/product")
	public String product(Model model) {
		List<Product> listPrd = this.productService.findAll();
		model.addAttribute("listPrd", listPrd);
		return "/views/main/product";
	}

	@RequestMapping("/product/{id}")
	public String productDetail(@PathVariable("id") Integer id, Model model) {
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		return "/views/main/productDetail";
	}

	@RequestMapping("/admin/product")
	public String admin() {
		return "/views/admin/product";
	}
}
