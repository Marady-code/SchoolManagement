package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;

public interface StudentService {
	
	StudentResponseDTO create(StudentRequestDTO dto);
	StudentResponseDTO getById(Long studentId);
	List<StudentResponseDTO> getAll();
	StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto);
	void deleteStudent(Long studentId);
}
