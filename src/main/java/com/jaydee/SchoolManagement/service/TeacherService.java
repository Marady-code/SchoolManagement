package com.jaydee.SchoolManagement.service;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;

public interface TeacherService {
	
	TeacherResponseDTO create(TeacherRequestDTO requestDTO);
}
