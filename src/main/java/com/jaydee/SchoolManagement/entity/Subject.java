package com.jaydee.SchoolManagement.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;
	
	@Column(unique = true, nullable = false)
	private String subjectName;
	
	private String description;
	
	private String subjectCode;
	
	@ManyToMany(mappedBy = "subjects")
	private Set<Teacher> teachers = new HashSet<>();
	
	@ManyToMany(mappedBy = "subjects")
	private Set<ClassEntity> classes = new HashSet<>();
	
	@OneToMany(mappedBy = "subject")
	private List<Exam> exams;
	
	private LocalDateTime createAt = LocalDateTime.now();
} 