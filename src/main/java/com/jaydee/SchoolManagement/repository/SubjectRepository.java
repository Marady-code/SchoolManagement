package com.jaydee.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jaydee.SchoolManagement.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject>{

	boolean existsBySubjectName(String subjectName);
	boolean existsByCode(String code);
	
}
