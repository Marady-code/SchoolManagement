package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ExamRequestDTO {
	
	private String examTitle;
	
	private String description;
	
	private Long subjectId;
	
	private Long classId;
	
	private LocalDate examDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Integer totalMarks;
	
	private String examType;
} 