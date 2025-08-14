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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	private String phone_number;
	
	private LocalDate date_of_birth;
	
	private String place_of_birth;
	
	private String current_place;
	
	private String emergencyPhone;
	
//	@ManyToOne
//	@JoinColumn(name = "class_id")
//	private ClassEntity classEntity;
	
	@ManyToMany(mappedBy = "students")
	private Set<ClassEntity> classes = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private List<Attendance> studentAttendance;
	
	private LocalDateTime createAt = LocalDateTime.now();
}
