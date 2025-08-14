package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BulkAttendanceSubmitRequest {
	
    private Long classId;
    private Long teacherId;
    private LocalDate date = LocalDate.now(); // Default to today
    private List<StudentAttendanceDTO> attendances;
}
