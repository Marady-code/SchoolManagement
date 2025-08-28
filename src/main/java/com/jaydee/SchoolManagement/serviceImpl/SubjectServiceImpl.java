package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
public class SubjectServiceImpl implements SubjectService {

	private final SubjectRepository subjectRepository;
	private final SubjectMapper subjectMapper;

	@Override
	public SubjectResponseDTO createSubject(SubjectRequestDTO requestDTO) {
		if (subjectRepository.existsBySubjectName(requestDTO.getSubjectName())) {
			throw new IllegalArgumentException("This subject already exists");
		}

		if (requestDTO.getCode() != null && subjectRepository.existsByCode(requestDTO.getCode())) {
			throw new IllegalArgumentException("Subject with this code already exists");
		}

		Subject subject = subjectMapper.toEntity(requestDTO);
		Subject saved = subjectRepository.save(subject);

		return subjectMapper.toResponseDTO(saved);
	}

	@Override
	public SubjectResponseDTO updateSubject(Long subjectId, SubjectRequestDTO requestDTO) {
		
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFound("Subject not found!"));
		
		if(!subject.getSubjectName().equals(requestDTO.getSubjectName()) && subjectRepository.existsBySubjectName(requestDTO.getSubjectName())) {
			throw new IllegalArgumentException("This Subject already exists");
		}
		
		if(requestDTO.getCode() != null && !requestDTO.getCode().equals(subject.getCode()) & subjectRepository.existsByCode(requestDTO.getCode())) {
			throw new IllegalArgumentException("Subject with this code already exists");
		}
		
		subject.setSubjectName(requestDTO.getSubjectName());
		subject.setCode(requestDTO.getCode());
		subject.setDescription(requestDTO.getDescription());
		
		Subject updated = subjectRepository.save(subject);
		return subjectMapper.toResponseDTO(updated);
	}

	@Override
	public void delete(Long subjectId) {
		Subject subject =subjectRepository.findById(subjectId)
				.orElseThrow(() ->  new ResourceNotFound("Subject", subjectId));
		subjectRepository.delete(subject);
	}
	

	@Override
	public SubjectResponseDTO getSubjectById(Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() ->  new ResourceNotFound("Subject", subjectId));
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
	public List<SubjectResponseDTO> findBySubjectName(String subjectName) {
		SubjectFilter filter = new SubjectFilter();
		filter.setSubjectName(subjectName);
		return findSubjectsByFilter(filter);
	}

	@Override
	public List<SubjectResponseDTO> findBySubjectCode(String code) {
		SubjectFilter filter = new SubjectFilter();
		filter.setCode(code);
		return findSubjectsByFilter(filter);
	}



}
