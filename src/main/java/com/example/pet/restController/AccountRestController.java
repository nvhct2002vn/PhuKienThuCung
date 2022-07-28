package com.example.pet.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.entities.Account;
import com.example.pet.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/account")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/{username}")
	public Account getByUserName(@PathVariable("username") String username) {
		return this.accountService.findByUsername(username);
	}
	@GetMapping("")
	public List<Account> findAll() {
		return this.accountService.findAll();
	}
	@PutMapping()
	public Account updateAccount(@RequestBody Account account) {
		return this.accountService.update(account);
	}
}
