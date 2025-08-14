package com.jaydee.SchoolManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@NotBlank
	@Size(min = 9, max = 10)
	private String phone_number;
	
	private LocalDate date_of_birth;
	
	private String place_of_birth;
	
	private String current_place;
	
	private String emergencyPhone;
	
	@ManyToMany(mappedBy = "students")
	private Set<ClassEntity> classes = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private List<Attendance> studentAttendance;
	
	@ManyToOne
	private Parent parent;
	
	@OneToMany(mappedBy = "student")
	private List<ExamResult> examResults;
	
	private LocalDateTime createAt = LocalDateTime.now();
}
