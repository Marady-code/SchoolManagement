package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceFilter {
    private Long studentId;
    private String studentName; // For case-insensitive search in student names
    private String studentFirstName; // For specific first name search
    private String studentLastName; // For specific last name search
    private Long classId;
    private String className; // For class name search
    private Long teacherId;
    private String teacherName; // For teacher name search
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private AttendanceStatus status;
    private String remark; // For remark search
    private LocalDate specificDate; // For exact date search
    private Boolean isPresent; // For present/absent filter
} 