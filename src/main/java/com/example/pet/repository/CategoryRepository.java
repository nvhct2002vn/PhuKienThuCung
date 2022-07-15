package com.example.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
