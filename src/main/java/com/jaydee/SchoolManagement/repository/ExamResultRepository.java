package com.jaydee.SchoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long>, JpaSpecificationExecutor<ExamResult> {
    
    List<ExamResult> findByStudentStudentId(Long studentId);
    
    List<ExamResult> findByExamExamId(Long examId);
    
    List<ExamResult> findByExamSubjectSubjectId(Long subjectId);
    
    List<ExamResult> findByStudentStudentIdAndExamSubjectSubjectId(Long studentId, Long subjectId);
    
    List<ExamResult> findByGrade(String grade);
    
    List<ExamResult> findByPercentageGreaterThanEqual(Double percentage);
    
    List<ExamResult> findByPercentageLessThan(Double percentage);
    
    List<ExamResult> findByStudentStudentIdAndExamExamId(Long studentId, Long examId);
    
    List<ExamResult> findByPercentageGreaterThanEqualAndPercentageLessThanEqual(Double minPercentage, Double maxPercentage);
} 