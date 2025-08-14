package com.jaydee.SchoolManagement.specification;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClassFilter {
    private String className; // For class name search (case-insensitive)
    private Long teacherId; // For classes taught by specific teacher
    private String teacherName; // For classes taught by teacher with specific name
    private String roomNumber; // For room number search
    private String classDay; // For class day filter
    private String classTime; // For class time search
    private LocalDate startDateFrom; // For start date range
    private LocalDate startDateTo; // For start date range
    private Long studentId; // For classes containing specific student
    private String studentName; // For classes containing student with specific name
    private LocalDate createdFrom; // For creation date range
    private LocalDate createdTo; // For creation date range
    private Boolean isActive; // For active/inactive classes (based on start date)
} 