package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.specification.SubjectFilter;

public interface SubjectService {

	SubjectResponseDTO createSubject(SubjectRequestDTO requestDTO);
	
	SubjectResponseDTO updateSubject(Long subjectId, SubjectRequestDTO requestDTO);
	
	void delete(Long subjectId);
	
	SubjectResponseDTO getSubjectById(Long subjectId);
	
	List<SubjectResponseDTO> getAllSubjects();
	
	List<SubjectResponseDTO> findSubjectsByFilter(SubjectFilter filter);
	
	List<SubjectResponseDTO> findBySubjectName(String subjectName);
	
	List<SubjectResponseDTO> findBySubjectCode(String code);
	
}
