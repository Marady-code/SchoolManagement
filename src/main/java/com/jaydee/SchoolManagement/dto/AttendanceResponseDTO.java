package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class AttendanceResponseDTO {
	
	private String studentName;	
	private String className;
	private String teacherName;
	private LocalDate date;
	private AttendanceStatus status;
	private String remark;
}
