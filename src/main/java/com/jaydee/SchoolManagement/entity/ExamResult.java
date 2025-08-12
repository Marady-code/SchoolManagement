package com.jaydee.SchoolManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exam_results")
public class ExamResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "result_id")
	private Long resultId;
	
	@ManyToOne
	private Exam exam;
	
	@ManyToOne
	private Student student;
	
	private Double obtainedMarks;
	
	private Double percentage;
	
	private String grade;
	
	private String remarks;
	
	private LocalDateTime createAt = LocalDateTime.now();
} 