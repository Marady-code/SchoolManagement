package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class AdminRequestDTO {
	
	private String firstName;
	
	private String lastName;
	
	private GenderEnum gender;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private String username;
	
	private String password;
	
	private String role;
} 