package com.jaydee.SchoolManagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.specification.TeacherFilter;

public interface TeacherService {
	
	TeacherResponseDTO create(TeacherRequestDTO requestDTO);
	TeacherResponseDTO getById(Long teacherId);
	List<TeacherResponseDTO> getAll();
	TeacherResponseDTO updateTeacher(Long teachId, TeacherRequestDTO dto);
	void deleteTeacher(Long teacherId);
	
	// New methods using JPA Specifications
	List<TeacherResponseDTO> findTeachersByFilter(TeacherFilter filter);
	List<TeacherResponseDTO> findTeachersByName(String name);
	List<TeacherResponseDTO> findTeachersByFullName(String fullName);
	List<TeacherResponseDTO> findTeachersByGender(GenderEnum gender);
	List<TeacherResponseDTO> findTeachersByQualification(String qualification);
	List<TeacherResponseDTO> findTeachersBySalaryRange(Long salaryFrom, Long salaryTo);
	List<TeacherResponseDTO> findTeachersByExperienceRange(Integer experienceFrom, Integer experienceTo);
	List<TeacherResponseDTO> findTeachersByJoiningDateRange(LocalDate dateFrom, LocalDate dateTo);
	List<TeacherResponseDTO> findTeachersByAgeRange(Integer ageFrom, Integer ageTo);
	List<TeacherResponseDTO> findTeachersByClass(String className);
	List<TeacherResponseDTO> findTeachersByStudent(String studentName);
	List<TeacherResponseDTO> findActiveTeachers();
	List<TeacherResponseDTO> findTeachersByPlace(String place);
	List<TeacherResponseDTO> findTeachersByPhoneNumber(String phoneNumber);
}
