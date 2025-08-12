package com.jaydee.SchoolManagement.dto;

import java.time.LocalDateTime;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class AdminResponseDTO {
	
	private Long adminId;
	
	private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	private GenderEnum gender;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private String username;
	
	private String role;
	
	private Boolean isActive;
	
	private LocalDateTime createAt;
} 