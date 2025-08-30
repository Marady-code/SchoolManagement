package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ClassResponseDTO {
	
	private String classId;
	
	private String className;
	
	private String roomNumber;
	
	private String classDay;
	
	private String classTime;
	
	private LocalDate startDate;
	
	private TeacherResponseDTO teacher;
	
	private List<ClassStudentsResponseDTO> students;
}
