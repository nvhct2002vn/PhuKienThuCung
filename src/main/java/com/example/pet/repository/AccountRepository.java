package com.example.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
