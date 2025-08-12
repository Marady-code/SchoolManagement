package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class StudentAttendanceDTO {
	
	private Long studentId;
	private AttendanceStatus status;
	private String remark;
}
