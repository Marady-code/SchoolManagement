package com.jaydee.SchoolManagement.dto;

import lombok.Data;

@Data
public class ExamResultRequestDTO {
	
	private Long examId;
	
	private Long studentId;
	
	private Double obtainedMarks;
	
	private String remarks;
} 