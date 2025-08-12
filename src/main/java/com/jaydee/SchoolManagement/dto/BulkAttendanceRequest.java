package com.jaydee.SchoolManagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class BulkAttendanceRequest {
	
	private Long classId;
	private List<StudentAttendanceDTO> attendaneList;
}
