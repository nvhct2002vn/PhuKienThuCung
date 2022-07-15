package com.example.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	private int role;
}
