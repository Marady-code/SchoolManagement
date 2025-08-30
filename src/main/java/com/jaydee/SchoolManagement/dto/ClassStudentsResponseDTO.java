package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class ClassStudentsResponseDTO {

	private Long studentId;
	private String fullName;
	private GenderEnum gender;
	private String phone_number;
}
