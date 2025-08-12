package com.jaydee.SchoolManagement.dto;

import java.time.LocalTime;

import com.jaydee.SchoolManagement.entity.DayOfWeek;

import lombok.Data;

@Data
public class TimeTableRequestDTO {
	
	private DayOfWeek dayOfWeek;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Long teacherId;
	
	private Long subjectId;
	
	private Long classId;
} 