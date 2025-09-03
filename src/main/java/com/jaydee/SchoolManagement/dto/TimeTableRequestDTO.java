package com.jaydee.SchoolManagement.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jaydee.SchoolManagement.entity.DayOfWeek;

import lombok.Data;

@Data
public class TimeTableRequestDTO {

	private DayOfWeek dayOfWeek;
	
	private Long subjectId;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalTime startTime;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalTime endTime;
	
	private String room;
	
	private Long teacherId;
	
	private Long classId;
}
