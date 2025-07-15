package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;

public interface TeacherService {
	
	TeacherResponseDTO create(TeacherRequestDTO requestDTO);
	TeacherResponseDTO getById(Long teacherId);
	List<TeacherResponseDTO> getAll();
	TeacherResponseDTO updateTeacher(Long teachId, TeacherRequestDTO dto);
	void deleteTeacher(Long teacherId);
}
