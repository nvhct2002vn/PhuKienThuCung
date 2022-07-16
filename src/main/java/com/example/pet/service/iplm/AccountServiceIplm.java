package com.example.pet.service.iplm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.entities.Account;
import com.example.pet.repository.AccountRepository;
import com.example.pet.service.AccountService;

@Service
public class AccountServiceIplm implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findByUsername(String username) {
		return this.accountRepository.findByUsername(username);
	}
}
