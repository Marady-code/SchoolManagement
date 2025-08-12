package com.jaydee.SchoolManagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jaydee.SchoolManagement.dto.ExamRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResponseDTO;
import com.jaydee.SchoolManagement.specification.ExamFilter;

public interface ExamService {
    
    ExamResponseDTO createExam(ExamRequestDTO requestDTO);
    
    ExamResponseDTO updateExam(Long id, ExamRequestDTO requestDTO);
    
    void deleteExam(Long id);
    
    ExamResponseDTO getExamById(Long id);
    
    List<ExamResponseDTO> getAllExams();
    
    List<ExamResponseDTO> findExamsByFilter(ExamFilter filter);
    
    List<ExamResponseDTO> findExamsBySubject(Long subjectId);
    
    List<ExamResponseDTO> findExamsByClass(Long classId);
    
    List<ExamResponseDTO> findExamsByDate(LocalDate examDate);
    
    List<ExamResponseDTO> findExamsByDateRange(LocalDate dateFrom, LocalDate dateTo);
    
    List<ExamResponseDTO> findExamsByType(String examType);
} 