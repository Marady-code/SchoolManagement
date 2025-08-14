package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ClassAttendanceSummaryDTO {

	private String className;
	private LocalDate date;
	private int presentCount;
	private int lateCount;
	private int absentCount;
	private List<AttendanceResponseDTO> details;
}
