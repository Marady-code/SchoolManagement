package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.GenderEnum;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentFilter {
	
	private String firstName; // For first name search
	private String lastName; // For last name search
	private String fullName; // For full name search (case-insensitive)
	private GenderEnum gender; // For gender filter
	private String phoneNumber; // For phone number search
	private LocalDate dateOfBirthFrom; // For date of birth range
	private LocalDate dateOfBirthTo; // For date of birth range
	private String placeOfBirth; // For place of birth search
	private String currentPlace; // For current place search
	private String emergencyPhone; // For emergency phone search
	private Long classId; // For students in specific class
	private String className; // For students in classes with specific name
	private Long teacherId; // For students taught by specific teacher
	private LocalDate createdFrom; // For creation date range
	private LocalDate createdTo; // For creation date range
	private Integer ageFrom; // For age range filter
	private Integer ageTo; // For age range filter
}
