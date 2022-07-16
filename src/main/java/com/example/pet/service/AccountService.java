package com.example.pet.service;

import com.example.pet.entities.Account;

public interface AccountService {
	Account findByUsername(String username);
}
