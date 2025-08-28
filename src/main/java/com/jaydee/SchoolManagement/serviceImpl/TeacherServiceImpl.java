package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.entity.Teacher;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.TeacherMapper;
import com.jaydee.SchoolManagement.repository.TeacherRepository;
import com.jaydee.SchoolManagement.service.TeacherService;
import com.jaydee.SchoolManagement.specification.TeacherFilter;
import com.jaydee.SchoolManagement.specification.TeacherSpec;
import com.jaydee.SchoolManagement.util.PageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

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
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFound("Teacher", teacherId));
		return teacherMapper.toResponseDTO(teacher);
	}

//	@Override
//	public List<TeacherResponseDTO> getAll() {
//		return teacherRepository.findAll()
//				.stream()
//				.map(teacherMapper::toResponseDTO)
//				.collect(Collectors.toList());
//	}

	@Override
	public PageResponse<TeacherResponseDTO> getAllTeachers(Pageable pageable) {
		Page<Teacher> page = teacherRepository.findAll(pageable);
		Page<TeacherResponseDTO> response = page.map(teacherMapper::toResponseDTO);
		return PageResponse.of(response);
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

	// New JPA Specification methods
	@Override
	public List<TeacherResponseDTO> findTeachersByFilter(TeacherFilter filter) {
		TeacherSpec spec = new TeacherSpec(filter);
		return teacherRepository.findAll(spec).stream().map(teacherMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByName(String name) {
		TeacherFilter filter = new TeacherFilter();
		filter.setFirstName(name);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByFullName(String fullName) {
		TeacherFilter filter = new TeacherFilter();
		filter.setFullName(fullName);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByGender(GenderEnum gender) {
		TeacherFilter filter = new TeacherFilter();
		filter.setGender(gender);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByQualification(String qualification) {
		TeacherFilter filter = new TeacherFilter();
		filter.setQualification(qualification);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersBySalaryRange(Long salaryFrom, Long salaryTo) {
		TeacherFilter filter = new TeacherFilter();
		filter.setSalaryFrom(salaryFrom);
		filter.setSalaryTo(salaryTo);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByExperienceRange(Integer experienceFrom, Integer experienceTo) {
		TeacherFilter filter = new TeacherFilter();
		filter.setExperienceYearsFrom(experienceFrom);
		filter.setExperienceYearsTo(experienceTo);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByJoiningDateRange(LocalDate dateFrom, LocalDate dateTo) {
		TeacherFilter filter = new TeacherFilter();
		filter.setJoiningDateFrom(dateFrom);
		filter.setJoiningDateTo(dateTo);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByAgeRange(Integer ageFrom, Integer ageTo) {
		TeacherFilter filter = new TeacherFilter();
		filter.setAgeFrom(ageFrom);
		filter.setAgeTo(ageTo);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByClass(String className) {
		TeacherFilter filter = new TeacherFilter();
		filter.setClassName(className);
		return findTeachersByFilter(filter);
	}

//	@Override
//	public List<TeacherResponseDTO> findTeachersByStudent(String studentName) {
//		TeacherFilter filter = new TeacherFilter();
//		filter.setStudentName(studentName);
//		return findTeachersByFilter(filter);
//	}
//
//	@Override
//	public List<TeacherResponseDTO> findActiveTeachers() {
//		TeacherFilter filter = new TeacherFilter();
//		filter.setIsActive(true);
//		return findTeachersByFilter(filter);
//	}

	@Override
	public List<TeacherResponseDTO> findTeachersByPlace(String place) {
		TeacherFilter filter = new TeacherFilter();
		filter.setCurrentPlace(place);
		return findTeachersByFilter(filter);
	}

	@Override
	public List<TeacherResponseDTO> findTeachersByPhoneNumber(String phoneNumber) {
		TeacherFilter filter = new TeacherFilter();
		filter.setPhoneNumber(phoneNumber);
		return findTeachersByFilter(filter);
	}

}
