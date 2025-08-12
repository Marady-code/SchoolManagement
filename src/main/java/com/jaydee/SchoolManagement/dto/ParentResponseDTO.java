package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class ParentResponseDTO {
	
	private Long parentId;
	
//	private String firstName;
//	
//	private String lastName;
	
	private String fullName;
	
	private GenderEnum gender;
	
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	private String occupation;
	
	private LocalDate dateOfBirth;
	
	private LocalDateTime createAt;
} 