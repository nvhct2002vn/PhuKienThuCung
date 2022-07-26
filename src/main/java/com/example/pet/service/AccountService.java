package com.example.pet.service;

import java.util.List;

import com.example.pet.entities.Account;

public interface AccountService {
	Account findByUsername(String username);

	List<Account> findAll();
}
