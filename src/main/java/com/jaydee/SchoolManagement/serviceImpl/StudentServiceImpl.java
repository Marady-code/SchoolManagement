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
		Student existing = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
				existing.setFirstName(dto.getFirstName());
				existing.setLastName(dto.getLastName());
				existing.setGender(dto.getGender());
				existing.setPhone_number(dto.getPhone_number());
				existing.setDate_of_birth(dto.getDate_of_birth());
				existing.setPlace_of_birth(dto.getPlace_of_birth());
				existing.setCurrent_place(dto.getCurrent_place());
				existing.setEmergencyPhone(dto.getEmergencyPhone());
				
		Student updated = studentRepository.save(existing);
		return studentMapper.toResponseDTO(updated);

	}

	@Override
	public void deleteStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
		
		studentRepository.delete(student);
	}

}
