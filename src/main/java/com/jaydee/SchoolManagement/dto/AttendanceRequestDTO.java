package com.jaydee.SchoolManagement.dto;

import java.time.LocalDateTime;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class AttendanceRequestDTO {
	
	private Long studentId;	
	private Long classId;
	private LocalDateTime date = LocalDateTime.now();
	private AttendanceStatus status;
	private String remark;
}
