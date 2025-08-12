package com.jaydee.SchoolManagement.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SubjectResponseDTO {
	
	private Long subjectId;
	
	private String subjectName;
	
	private String description;
	
	private String subjectCode;
	
	private LocalDateTime createAt;
} 