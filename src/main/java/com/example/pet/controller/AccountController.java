package com.example.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pet.AuthService;

@Controller
public class AccountController {
	@Autowired
	AuthService authService;

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		authService.loginFromOauth2(oauth2);
		return "redirect:/";
	}
	
}
