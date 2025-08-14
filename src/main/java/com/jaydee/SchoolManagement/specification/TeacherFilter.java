package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.GenderEnum;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherFilter {
    private String firstName; // For first name search
    private String lastName; // For last name search
    private String fullName; // For full name search (case-insensitive)
    private GenderEnum gender; // For gender filter
    private String phoneNumber; // For phone number search
    private LocalDate dateOfBirthFrom; // For date of birth range
    private LocalDate dateOfBirthTo; // For date of birth range
    private String placeOfBirth; // For place of birth search
    private String currentPlace; // For current place search
    private String qualification; // For qualification search
    private LocalDate joiningDateFrom; // For joining date range
    private LocalDate joiningDateTo; // For joining date range
    private Long salaryFrom; // For salary range
    private Long salaryTo; // For salary range
    private Long classId; // For teachers teaching specific class
    private String className; // For teachers teaching classes with specific name
    private Long studentId; // For teachers teaching specific student
    private String studentName; // For teachers teaching student with specific name
    private LocalDate createdFrom; // For creation date range
    private LocalDate createdTo; // For creation date range
    private Integer ageFrom; // For age range filter
    private Integer ageTo; // For age range filter
    private Boolean isActive; // For active/inactive teachers (based on joining date)
    private Integer experienceYearsFrom; // For experience range
    private Integer experienceYearsTo; // For experience range
} 