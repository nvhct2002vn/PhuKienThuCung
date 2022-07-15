package com.example.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
