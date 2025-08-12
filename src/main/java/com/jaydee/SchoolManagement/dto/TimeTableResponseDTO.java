package com.jaydee.SchoolManagement.dto;

import java.time.LocalTime;

import com.jaydee.SchoolManagement.entity.DayOfWeek;

import lombok.Data;

@Data
public class TimeTableResponseDTO {
	
	private Long scheduleId;
	
	private DayOfWeek dayOfWeek;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private String teacherName;
	
	private String subjectName;
	
	private String className;
} 