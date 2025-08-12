package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.entity.Subject;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.SubjectMapper;
import com.jaydee.SchoolManagement.repository.SubjectRepository;
import com.jaydee.SchoolManagement.service.SubjectService;
import com.jaydee.SchoolManagement.specification.SubjectFilter;
import com.jaydee.SchoolManagement.specification.SubjectSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {
    
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponseDTO createSubject(SubjectRequestDTO requestDTO) {
        // Check if subject name already exists
        if (subjectRepository.existsBySubjectName(requestDTO.getSubjectName())) {
            throw new IllegalArgumentException("Subject with this name already exists");
        }
        
        // Check if subject code already exists
        if (requestDTO.getSubjectCode() != null && subjectRepository.existsBySubjectCode(requestDTO.getSubjectCode())) {
            throw new IllegalArgumentException("Subject with this code already exists");
        }
        
        Subject subject = subjectMapper.toEntity(requestDTO);
        Subject saved = subjectRepository.save(subject);
        return subjectMapper.toResponseDTO(saved);
    }

    @Override
    public SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO requestDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Subject not found"));
        
        // Check if new name conflicts with existing subject
        if (!subject.getSubjectName().equals(requestDTO.getSubjectName()) && 
            subjectRepository.existsBySubjectName(requestDTO.getSubjectName())) {
            throw new IllegalArgumentException("Subject with this name already exists");
        }
        
        // Check if new code conflicts with existing subject
        if (requestDTO.getSubjectCode() != null && 
            !requestDTO.getSubjectCode().equals(subject.getSubjectCode()) && 
            subjectRepository.existsBySubjectCode(requestDTO.getSubjectCode())) {
            throw new IllegalArgumentException("Subject with this code already exists");
        }
        
        subject.setSubjectName(requestDTO.getSubjectName());
        subject.setSubjectCode(requestDTO.getSubjectCode());
        subject.setDescription(requestDTO.getDescription());
        
        Subject updated = subjectRepository.save(subject);
        return subjectMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Subject not found"));
        subjectRepository.delete(subject);
    }

    @Override
    public SubjectResponseDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Subject not found"));
        return subjectMapper.toResponseDTO(subject);
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectResponseDTO> findSubjectsByFilter(SubjectFilter filter) {
        SubjectSpec spec = new SubjectSpec(filter);
        return subjectRepository.findAll(spec)
                .stream()
                .map(subjectMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectResponseDTO> findSubjectsByName(String subjectName) {
        SubjectFilter filter = new SubjectFilter();
        filter.setSubjectName(subjectName);
        return findSubjectsByFilter(filter);
    }

    @Override
    public List<SubjectResponseDTO> findSubjectsByCode(String subjectCode) {
        SubjectFilter filter = new SubjectFilter();
        filter.setSubjectCode(subjectCode);
        return findSubjectsByFilter(filter);
    }
} 