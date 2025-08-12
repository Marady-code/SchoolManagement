package com.jaydee.SchoolManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "parents")
public class Parent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_id")
	private Long parentId;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@NotBlank
	@Size(min = 9, max = 10)
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	private String occupation;
	
	private LocalDate dateOfBirth;
	
	@OneToMany(mappedBy = "parent")
	private List<Student> children;
	
	private LocalDateTime createAt = LocalDateTime.now();
} 