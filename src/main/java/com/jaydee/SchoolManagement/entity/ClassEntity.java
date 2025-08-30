package com.jaydee.SchoolManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "classes")
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private Long classId;

	private String className;

	private String roomNumber;

	private String classDay;

	private String classTime;

	private LocalDate startDate;

	@OneToMany
	@JoinTable(name = "class_students", 
				joinColumns = @JoinColumn(name = "class_id"), 
				inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "classEntity")
	private List<Attendance> attendances;

	@ManyToMany
	@JoinTable(name = "class_subject", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;

	private LocalDateTime createAt = LocalDateTime.now();
}
