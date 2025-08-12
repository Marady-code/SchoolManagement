package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.ExamResultRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResultResponseDTO;
import com.jaydee.SchoolManagement.specification.ExamResultFilter;

public interface ExamResultService {
    
    ExamResultResponseDTO createExamResult(ExamResultRequestDTO requestDTO);
    
    ExamResultResponseDTO updateExamResult(Long id, ExamResultRequestDTO requestDTO);
    
    void deleteExamResult(Long id);
    
    ExamResultResponseDTO getExamResultById(Long id);
    
    List<ExamResultResponseDTO> getAllExamResults();
    
    List<ExamResultResponseDTO> findExamResultsByFilter(ExamResultFilter filter);
    
    List<ExamResultResponseDTO> findExamResultsByStudent(Long studentId);
    
    List<ExamResultResponseDTO> findExamResultsByExam(Long examId);
    
    List<ExamResultResponseDTO> findExamResultsBySubject(Long subjectId);
    
    List<ExamResultResponseDTO> findExamResultsByGrade(String grade);
    
    List<ExamResultResponseDTO> findExamResultsByPercentageRange(Double minPercentage, Double maxPercentage);
} 