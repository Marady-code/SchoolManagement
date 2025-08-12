package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ExamResponseDTO {
	
	private Long examId;
	
	private String examTitle;
	
	private String description;
	
	private String subjectName;
	
	private String className;
	
	private LocalDate examDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Integer totalMarks;
	
	private String examType;
	
	private LocalDateTime createAt;
} 