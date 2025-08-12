package com.jaydee.SchoolManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    
    Optional<Subject> findBySubjectName(String subjectName);
    
    Optional<Subject> findBySubjectCode(String subjectCode);
    
    List<Subject> findBySubjectNameContainingIgnoreCase(String subjectName);
    
    boolean existsBySubjectName(String subjectName);
    
    boolean existsBySubjectCode(String subjectCode);
} 