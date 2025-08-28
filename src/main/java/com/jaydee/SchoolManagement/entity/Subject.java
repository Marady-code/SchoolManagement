package com.jaydee.SchoolManagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

	@Column(name = "subject_name")
	private String subjectName;

	private String description;

	@Column(name = "subject_code")
	private String code;

	@ManyToMany(mappedBy = "subjects")
	private List<Teacher> teachers;

	@ManyToMany(mappedBy = "subjects")
	private List<ClassEntity> classEntities;

	@JsonFormat(pattern = "MMM dd, yyyy 'at' hh:mm a", timezone = "Asia/Phnom_Penh")
	private LocalDateTime createAt = LocalDateTime.now();

}
