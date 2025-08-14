package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class AttendanceRequestDTO {
	
	private Long studentId;	
	private Long classId;
	private LocalDate date;
	private AttendanceStatus status;
	private String remark;
}
