package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class AttendanceRequestDTO {
	
	private Long studentId;	
	private Long classId;
	private Long teacherId;
	private AttendanceStatus status = AttendanceStatus.PRESENT;
	private String remark;

}
