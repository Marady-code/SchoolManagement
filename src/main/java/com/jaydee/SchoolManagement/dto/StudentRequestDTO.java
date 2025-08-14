package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class StudentRequestDTO {
	
	private String firstName;
	
	private String lastName;
	
	private GenderEnum gender;
	
	private String phone_number;
	
	private LocalDate date_of_birth;
	
	private String place_of_birth;
	
	private String current_place;
	
	private String emergencyPhone;
}
