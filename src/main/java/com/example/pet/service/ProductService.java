package com.example.pet.service;

import java.util.List;

import com.example.pet.entities.Category;
import com.example.pet.entities.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	Product save(Product product);

	Product update(Product product);

}
