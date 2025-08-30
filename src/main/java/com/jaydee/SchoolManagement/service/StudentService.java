package com.jaydee.SchoolManagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.specification.StudentFilter;
import com.jaydee.SchoolManagement.util.PageResponse;

public interface StudentService {

	StudentResponseDTO create(StudentRequestDTO dto);

	StudentResponseDTO getById(Long studentId);

	StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto);

	PageResponse<StudentResponseDTO> getAllStudents(Pageable pageable);

	// New methods using JPA Specifications
	List<StudentResponseDTO> findStudentsByFilter(StudentFilter filter);

	List<StudentResponseDTO> findStudentsByName(String name);

	List<StudentResponseDTO> findStudentsByFullName(String fullName);

	List<StudentResponseDTO> findStudentsByGender(GenderEnum gender);

	List<StudentResponseDTO> findStudentsByPhoneNumber(String phoneNumber);

	List<StudentResponseDTO> findStudentsByPlace(String place);
	
	List<StudentResponseDTO> findStudentsByAgeRange(Integer ageFrom, Integer ageTo);

//	List<StudentResponseDTO> findStudentsByTeacher(String teacherName);
//	List<StudentResponseDTO> findStudentsByDateOfBirthRange(LocalDate dateFrom, LocalDate dateTo);
//	List<StudentResponseDTO> findStudentsByCreationDateRange(LocalDate dateFrom, LocalDate dateTo);

	void deleteStudent(Long studentId);
//	
//	/**
//	 * Soft deletes a student by marking it as deleted without removing from database
//	 * @param studentId ID of the student to soft delete
//	 */
//	void softDeleteStudent(Long studentId);
//	
//	/**
//	 * Restores a previously soft-deleted student
//	 * @param studentId ID of the student to restore
//	 * @return The restored student data
//	 */
//	StudentResponseDTO restoreStudent(Long studentId);
//	
//	/**
//	 * Retrieves all soft-deleted students
//	 * @return List of soft-deleted students
//	 */
//	List<StudentResponseDTO> getAllDeletedStudents();
}
