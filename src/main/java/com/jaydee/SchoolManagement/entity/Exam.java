package com.jaydee.SchoolManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id")
	private Long examId;
	
	@Column(nullable = false)
	private String examTitle;
	
	private String description;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private ClassEntity classEntity;
	
	private LocalDate examDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Integer totalMarks;
	
	private String examType; // MID_TERM, FINAL, QUIZ, etc.
	
	@OneToMany(mappedBy = "exam")
	private List<ExamResult> examResults;
	
	private LocalDateTime createAt = LocalDateTime.now();
} 