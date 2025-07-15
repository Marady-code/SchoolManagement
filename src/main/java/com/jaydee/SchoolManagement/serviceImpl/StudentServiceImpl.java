package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.StudentMapper;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;

	@Override
	public StudentResponseDTO create(StudentRequestDTO dto) {
		Student student = studentMapper.toEntity(dto);
		Student saved = studentRepository.save(student);
		return studentMapper.toResponseDTO(saved);
	}

	@Override
	public StudentResponseDTO getById(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
		return studentMapper.toResponseDTO(student);
	}

	@Override
	public List<StudentResponseDTO> getAll() {
		return studentRepository.findAll()
				.stream()
				.map(studentMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
		
		studentRepository.delete(student);
	}

}
