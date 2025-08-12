package com.jaydee.SchoolManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Long adminId;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank
	@Size(min = 9, max = 10)
	private String phoneNumber;
	
	private String address;
	
	private String username;
	
	private String password;
	
	private String role; // SUPER_ADMIN, ADMIN, etc.
	
	private Boolean isActive = true;
	
	private LocalDateTime createAt = LocalDateTime.now();
} 