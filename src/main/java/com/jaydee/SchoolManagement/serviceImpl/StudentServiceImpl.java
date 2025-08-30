package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.StudentMapper;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.service.StudentService;
import com.jaydee.SchoolManagement.specification.StudentFilter;
import com.jaydee.SchoolManagement.specification.StudentSpec;
import com.jaydee.SchoolManagement.util.PageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

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

//	@Override
//	public List<StudentResponseDTO> getAll() {
//		return studentRepository.findAll()
//				.stream()
//				.map(studentMapper::toResponseDTO)
//				.collect(Collectors.toList());
//	}

	@Override
	public PageResponse<StudentResponseDTO> getAllStudents(Pageable pageable) {
		Page<Student> page = studentRepository.findAll(pageable);
		Page<StudentResponseDTO> response = page.map(studentMapper::toResponseDTO);
		return PageResponse.of(response);
	}

	@Override
	public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
		studentMapper.updateStudentFromDto(dto, student);
		Student updated = studentRepository.save(student);
		return studentMapper.toResponseDTO(updated);

	}

	@Override
	public void deleteStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
		studentRepository.delete(student);
	}

	// New JPA Specification methods
	@Override
	public List<StudentResponseDTO> findStudentsByFilter(StudentFilter filter) {
		StudentSpec spec = new StudentSpec(filter);
		return studentRepository.findAll(spec).stream().map(studentMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public List<StudentResponseDTO> findStudentsByName(String name) {
		StudentFilter filter = new StudentFilter();
		filter.setFirstName(name);
		return findStudentsByFilter(filter);
	}

	@Override
	public List<StudentResponseDTO> findStudentsByFullName(String fullName) {
		StudentFilter filter = new StudentFilter();
		filter.setFullName(fullName);
		return findStudentsByFilter(filter);
	}

	@Override
	public List<StudentResponseDTO> findStudentsByGender(GenderEnum gender) {
		StudentFilter filter = new StudentFilter();
		filter.setGender(gender);
		return findStudentsByFilter(filter);
	}

	@Override
	public List<StudentResponseDTO> findStudentsByAgeRange(Integer ageFrom, Integer ageTo) {
		StudentFilter filter = new StudentFilter();
		filter.setAgeFrom(ageFrom);
		filter.setAgeTo(ageTo);
		return findStudentsByFilter(filter);
	}

	@Override
	public List<StudentResponseDTO> findStudentsByPhoneNumber(String phoneNumber) {
		StudentFilter filter = new StudentFilter();
		filter.setPhoneNumber(phoneNumber);
		return findStudentsByFilter(filter);
	}

	@Override
	public List<StudentResponseDTO> findStudentsByPlace(String place) {
		StudentFilter filter = new StudentFilter();
		filter.setCurrentPlace(place);
		return findStudentsByFilter(filter);
	}

//	@Override
//	public List<StudentResponseDTO> findStudentsByDateOfBirthRange(LocalDate dateFrom, LocalDate dateTo) {
//		StudentFilter filter = new StudentFilter();
//		filter.setDateOfBirthFrom(dateFrom);
//		filter.setDateOfBirthTo(dateTo);
//		return findStudentsByFilter(filter);
//	}
//
//	@Override
//	public List<StudentResponseDTO> findStudentsByCreationDateRange(LocalDate dateFrom, LocalDate dateTo) {
//		StudentFilter filter = new StudentFilter();
//		filter.setCreatedFrom(dateFrom);
//		filter.setCreatedTo(dateTo);
//		return findStudentsByFilter(filter);
//	}

//	@Override
//	@Transactional
//	public void softDeleteStudent(Long studentId) {
//		// Check if student exists and is not already deleted
//		studentRepository.findById(studentId)
//				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
//		
//		// Perform soft delete
//		studentRepository.softDelete(studentId, LocalDateTime.now());
//	}
//	
//	@Override
//	@Transactional
//	public StudentResponseDTO restoreStudent(Long studentId) {
//		// Check if student exists
//		Student student = studentRepository.findById(studentId)
//				.orElseThrow(() -> new ResourceNotFound("Student", studentId));
//		
//		// Restore the student
//		studentRepository.restore(studentId);
//		
//		// Fetch and return the restored student
//		return studentMapper.toResponseDTO(student);
//	}
//	
//	@Override
//	public List<StudentResponseDTO> getAllDeletedStudents() {
//		return studentRepository.findAllDeleted()
//				.stream()
//				.map(studentMapper::toResponseDTO)
//				.collect(Collectors.toList());
//	}
	
//	@Override
//	public List<StudentResponseDTO> findStudentsByTeacher(String teacherName) {
//		StudentFilter filter = new StudentFilter();
//		filter.setTeacherId(null); // This would need to be implemented differently
//		return findStudentsByFilter(filter);
//	}
}
