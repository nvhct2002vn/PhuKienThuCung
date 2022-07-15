package com.example.pet.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private String name;
	private String image;
	private int price;
	private int quantity;
	private Date created_date;
	private int status;
}
