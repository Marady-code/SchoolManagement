package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.entity.Subject;
import com.jaydee.SchoolManagement.mapper.SubjectMapper;
import com.jaydee.SchoolManagement.repository.SubjectRepository;
import com.jaydee.SchoolManagement.service.SubjectService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long subjectId) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubjectResponseDTO getSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectResponseDTO> getAllSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
