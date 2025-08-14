package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClassRequestDTO {

	private String className;
	private String roomNumber;
	private String classDay;
	private String classTime;
	private LocalDate startDate;
}
