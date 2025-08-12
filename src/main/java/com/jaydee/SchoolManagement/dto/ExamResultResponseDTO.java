package com.jaydee.SchoolManagement.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExamResultResponseDTO {
	
	private Long resultId;
	
	private String examTitle;
	
	private String studentName;
	
	private String subjectName;
	
	private Double obtainedMarks;
	
	private Double percentage;
	
	private String grade;
	
	private String remarks;
	
	private LocalDateTime createAt;
} 