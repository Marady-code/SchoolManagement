package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class StudentResponseDTO {

	private Long studentId;
	
	private String fullName;
	
//	private String firstName;
//	
//	private String lastName;
	
	private GenderEnum gender;
	
	private LocalDate date_of_birth;
	
	private String phone_number;
	
	private String emergencyPhone;
}
