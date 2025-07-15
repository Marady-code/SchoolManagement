package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.Teacher;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.TeacherMapper;
import com.jaydee.SchoolManagement.repository.TeacherRepository;
import com.jaydee.SchoolManagement.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private final TeacherRepository teacherRepository;
	@Autowired
	private final TeacherMapper teacherMapper;

	@Override
	public TeacherResponseDTO create(TeacherRequestDTO requestDTO) {
		Teacher teacher = teacherMapper.toEntity(requestDTO);
		Teacher saved = teacherRepository.save(teacher);
		return teacherMapper.toResponseDTO(saved);
	}

	@Override
	public TeacherResponseDTO getById(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFound("Teacher", teacherId));
		return teacherMapper.toResponseDTO(teacher);		
	}

	@Override
	public List<TeacherResponseDTO> getAll() {
		return teacherRepository.findAll()
				.stream()
				.map(teacherMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public TeacherResponseDTO updateTeacher(Long teachId, TeacherRequestDTO dto) {
		Teacher existing = teacherRepository.findById(teachId)
				.orElseThrow(() -> new ResourceNotFound("Teacher", teachId));
				existing.setFirstName(dto.getFirstName());
				existing.setLastName(dto.getLastName());
				existing.setGender(dto.getGender());
				existing.setPhone_number(dto.getPhone_number());
				existing.setDate_of_birth(dto.getDate_of_birth());
				existing.setPlace_of_birth(dto.getPlace_of_birth());
				existing.setCurrent_place(dto.getCurrent_place());
				existing.setQualification(dto.getQualification());
				existing.setJoining_date(dto.getJoining_date());
				existing.setSalary(dto.getSalary());
		
		Teacher updated = teacherRepository.save(existing);
		return teacherMapper.toResponseDTO(updated);
	}

	@Override
	public void deleteTeacher(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFound("Teacher", teacherId));
		teacherRepository.delete(teacher);
		
	}
	
}
