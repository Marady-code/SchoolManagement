package com.jaydee.SchoolManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>, JpaSpecificationExecutor<Exam> {
    
    List<Exam> findBySubjectSubjectId(Long subjectId);
    
    List<Exam> findByClassEntityClassId(Long classId);
    
    List<Exam> findByExamDate(LocalDate examDate);
    
    List<Exam> findByExamType(String examType);
    
    List<Exam> findBySubjectSubjectNameContainingIgnoreCase(String subjectName);
    
    List<Exam> findByClassEntityClassNameContainingIgnoreCase(String className);
    
    List<Exam> findByExamDateBetween(LocalDate startDate, LocalDate endDate);
} 