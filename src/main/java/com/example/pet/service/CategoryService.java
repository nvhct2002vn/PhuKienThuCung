package com.example.pet.service;

import java.util.List;

import com.example.pet.entities.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findById(Integer id);

	Category save(Category category);
}
