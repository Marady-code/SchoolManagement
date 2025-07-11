package com.jaydee.SchoolManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.Teacher;
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
	
//	public TeacherResponseDTO createTeacher(TeacherRequestDTO dto) {
//		Teacher teacher = teacherMapper.toEntity(dto);
//		Teacher saved = teacherRepository.save(teacher);
//		return teacherMapper.toResponseDTO(saved);
//	}

	@Override
	public TeacherResponseDTO create(TeacherRequestDTO requestDTO) {
		Teacher teacher = teacherMapper.toEntity(requestDTO);
		Teacher saved = teacherRepository.save(teacher);
		return teacherMapper.toResponseDTO(saved);
	}
	
}
