package com.jaydee.SchoolManagement.specification;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamFilter {
    
    private String examTitle;
    
    private String examType;
    
    private Long subjectId;
    
    private Long classId;
    
    private LocalDate examDate;
    
    private LocalDate dateFrom;
    
    private LocalDate dateTo;
} 