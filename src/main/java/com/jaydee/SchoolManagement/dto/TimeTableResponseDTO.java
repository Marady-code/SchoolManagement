package com.jaydee.SchoolManagement.dto;


import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jaydee.SchoolManagement.entity.DayOfWeek;

import lombok.Data;

@Data
public class TimeTableResponseDTO {

	//private Long scheduleId;
	
	private DayOfWeek dayOfWeek;
	
	private String subjectName;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalTime startTime;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalTime endTime;
	
	private String room;
	
	private String teacherName;
	
	private String className;
}
