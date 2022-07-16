package com.example.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.pet.entities.Account;
import com.example.pet.repository.AccountRepository;
import com.example.pet.service.AccountService;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = this.accountService.findByUsername(username);
			String password = account.getPassword();
			int role = account.getRole();
			return User.withUsername(username).password(pe.encode(password)).roles(String.valueOf(role)).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		}
	}

	public void loginFromOauth2(OAuth2AuthenticationToken oauth2) {

		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		UserDetails acc = User.withUsername(email).password(pe.encode(password)).roles("0").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(acc, null, acc.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

}
