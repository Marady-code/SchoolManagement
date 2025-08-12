package com.jaydee.SchoolManagement.specification;

import lombok.Data;

@Data
public class ExamResultFilter {
    
    private Long studentId;
    
    private Long examId;
    
    private Long subjectId;
    
    private String grade;
    
    private Double minPercentage;
    
    private Double maxPercentage;
} 