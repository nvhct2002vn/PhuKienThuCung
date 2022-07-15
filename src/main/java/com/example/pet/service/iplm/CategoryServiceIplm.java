package com.example.pet.service.iplm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.entities.Category;
import com.example.pet.repository.CategoryRepository;
import com.example.pet.service.CategoryService;

@Service
public class CategoryServiceIplm implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	@Override
	public Category findById(Integer id) {
		return this.categoryRepository.findById(id).get();
	}

	@Override
	public Category save(Category category) {
		return this.categoryRepository.save(category);
	}

}
