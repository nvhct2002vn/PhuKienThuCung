package com.example.pet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Account;
import com.example.pet.service.AccountService;

@RestController
@CrossOrigin("*")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/rest/account/{username}")
	public Account getByUserName(@PathVariable("username") String username) {
		return this.accountService.findByUsername(username);
	}
}
