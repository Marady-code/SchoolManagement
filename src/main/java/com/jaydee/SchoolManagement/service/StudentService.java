package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.specification.StudentFilter;

public interface StudentService {
	
	StudentResponseDTO create(StudentRequestDTO dto);
	StudentResponseDTO getById(Long studentId);
	List<StudentResponseDTO> getAll();
	StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto);
	void deleteStudent(Long studentId);
	
	// New methods using JPA Specifications
	List<StudentResponseDTO> findStudentsByFilter(StudentFilter filter);
	List<StudentResponseDTO> findStudentsByName(String name);
	List<StudentResponseDTO> findStudentsByFullName(String fullName);
	List<StudentResponseDTO> findStudentsByGender(GenderEnum gender);
//	List<StudentResponseDTO> findStudentsByAgeRange(Integer ageFrom, Integer ageTo);
	List<StudentResponseDTO> findStudentsByClass(String className);
//	List<StudentResponseDTO> findStudentsByTeacher(String teacherName);
	List<StudentResponseDTO> findStudentsByPhoneNumber(String phoneNumber);
	List<StudentResponseDTO> findStudentsByPlace(String place);
//	List<StudentResponseDTO> findStudentsByDateOfBirthRange(LocalDate dateFrom, LocalDate dateTo);
//	List<StudentResponseDTO> findStudentsByCreationDateRange(LocalDate dateFrom, LocalDate dateTo);
}
