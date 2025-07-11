package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class TeacherResponseDTO {
	
	private Long teacherId;
	
	private String firstName;
	
	private String lastName;
	
	private GenderEnum gender;
	
	private String phone_number;
	
	private String qualification;
	
}
