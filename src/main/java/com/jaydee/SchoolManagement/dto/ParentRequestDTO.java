package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class ParentRequestDTO {
	
	private String firstName;
	
	private String lastName;
	
	private GenderEnum gender;
	
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	private String occupation;
	
	private LocalDate dateOfBirth;
} 