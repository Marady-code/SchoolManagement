package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.entity.Parent;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.StudentMapper;
import com.jaydee.SchoolManagement.repository.ParentRepository;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.service.StudentService;
import com.jaydee.SchoolManagement.specification.StudentFilter;
import com.jaydee.SchoolManagement.specification.StudentSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	private final ParentRepository parentRepository;

	@Override
	public StudentResponseDTO create(StudentRequestDTO dto) {
		Student student = studentMapper.toEntity(dto);
		
		// Set parent if parentId is provided
		if (dto.getParentId() != null) {
			Parent parent = parentRepository.findById(dto.getParentId())
					.orElseThrow(() -> new ResourceNotFound("Parent not found"));
			student.setParent(parent);
		}
		
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
				
				// Update parent if parentId is provided
				if (dto.getParentId() != null) {
					Parent parent = parentRepository.findById(dto.getParentId())
							.orElseThrow(() -> new ResourceNotFound("Parent not found"));
					existing.setParent(parent);
				}
				
		Student updated = studentRepository.save(existing);
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
		return studentRepository.findAll(spec)
				.stream()
				.map(studentMapper::toResponseDTO)
				.collect(Collectors.toList());
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

//	@Override
//	public List<StudentResponseDTO> findStudentsByAgeRange(Integer ageFrom, Integer ageTo) {
//		StudentFilter filter = new StudentFilter();
//		filter.setAgeFrom(ageFrom);
//		filter.setAgeTo(ageTo);
//		return findStudentsByFilter(filter);
//	}

	@Override
	public List<StudentResponseDTO> findStudentsByClass(String className) {
		StudentFilter filter = new StudentFilter();
		filter.setClassName(className);
		return findStudentsByFilter(filter);
	}

//	@Override
//	public List<StudentResponseDTO> findStudentsByTeacher(String teacherName) {
//		StudentFilter filter = new StudentFilter();
//		filter.setTeacherId(null); // This would need to be implemented differently
//		return findStudentsByFilter(filter);
//	}

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
}
