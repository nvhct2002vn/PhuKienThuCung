package com.example.pet.service.iplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.entities.Category;
import com.example.pet.entities.Product;
import com.example.pet.repository.ProductRepository;
import com.example.pet.service.ProductService;

@Service
public class ProductServiceIplm implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return this.productRepository.findById(id).get();
	}

	@Override
	public Product save(Category category) {
		return this.save(category);
	}

}