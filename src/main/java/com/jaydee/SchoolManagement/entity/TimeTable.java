package com.jaydee.SchoolManagement.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schedule_id;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	
	private LocalTime startTime;
	private LocalTime endTime;
	
	@ManyToOne
	private Teacher teacher;
	
	@ManyToOne
	private ClassEntity classEntity;
	
}
