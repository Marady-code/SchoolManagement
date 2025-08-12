package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.specification.SubjectFilter;

public interface SubjectService {
    
    SubjectResponseDTO createSubject(SubjectRequestDTO requestDTO);
    
    SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO requestDTO);
    
    void deleteSubject(Long id);
    
    SubjectResponseDTO getSubjectById(Long id);
    
    List<SubjectResponseDTO> getAllSubjects();
    
    List<SubjectResponseDTO> findSubjectsByFilter(SubjectFilter filter);
    
    List<SubjectResponseDTO> findSubjectsByName(String subjectName);
    
    List<SubjectResponseDTO> findSubjectsByCode(String subjectCode);
} 