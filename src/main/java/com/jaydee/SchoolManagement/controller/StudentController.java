package com.jaydee.SchoolManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.service.StudentService;
import com.jaydee.SchoolManagement.specification.StudentFilter;
import com.jaydee.SchoolManagement.util.PageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentRequestDTO dto) {
		StudentResponseDTO create = studentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable Long studentId) {
		StudentResponseDTO response = studentService.getById(studentId);
		return ResponseEntity.ok(response);
	}

	/**
	 * Get all students with pagination and sorting support
	 * 
	 * @param page    Page number (0-based) - default is 0
	 * @param size    Number of items per page - default is 10
	 * @param sortBy  Field to sort by - default is studentId
	 * @param sortDir Sort direction (asc/desc) - default is asc
	 * @return PageResponse containing student data and pagination metadata
	 */
	@GetMapping
	public ResponseEntity<PageResponse<StudentResponseDTO>> getAllStudent(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "studentId") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {

		// Create Sort object based on direction
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// Create PageRequest object with page number, size and sort
		PageRequest pageRequest = PageRequest.of(page, size, sort);

		// Get paginated response from service
		PageResponse<StudentResponseDTO> response = studentService.getAllStudents(pageRequest);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<?> update(@PathVariable Long studentId, @RequestBody StudentRequestDTO dto) {
		return ResponseEntity.ok(studentService.updateStudent(studentId, dto));
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<?> delete(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}

	// New endpoints using JPA Specifications

	@PostMapping("/filter")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByFilter(@RequestBody StudentFilter filter) {
		return ResponseEntity.ok(studentService.findStudentsByFilter(filter));
	}

	@GetMapping("/search/name")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByName(@RequestParam String name) {
		return ResponseEntity.ok(studentService.findStudentsByName(name));
	}

	@GetMapping("/search/fullname")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByFullName(@RequestParam String fullName) {
		return ResponseEntity.ok(studentService.findStudentsByFullName(fullName));
	}

	@GetMapping("/search/gender/{gender}")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByGender(@PathVariable String gender) {
		GenderEnum genderEnum = GenderEnum.valueOf(gender.toUpperCase());
		return ResponseEntity.ok(studentService.findStudentsByGender(genderEnum));
	}

	@GetMapping("/search/phone")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByPhoneNumber(@RequestParam String phoneNumber) {
		return ResponseEntity.ok(studentService.findStudentsByPhoneNumber(phoneNumber));
	}

	@GetMapping("/search/current_place")
	public ResponseEntity<List<StudentResponseDTO>> findStudentsByPlace(@RequestParam String place) {
		return ResponseEntity.ok(studentService.findStudentsByPlace(place));
	}

//	@GetMapping("/search/age-range")
//	public ResponseEntity<List<StudentResponseDTO>> findStudentsByAgeRange(
//			@RequestParam Integer ageFrom, 
//			@RequestParam Integer ageTo) {
//		return ResponseEntity.ok(studentService.findStudentsByAgeRange(ageFrom, ageTo));
//	}

//	@GetMapping("/search/dob-range")
//	public ResponseEntity<List<StudentResponseDTO>> findStudentsByDateOfBirthRange(
//			@RequestParam String dateFrom, 
//			@RequestParam String dateTo) {
//		LocalDate from = LocalDate.parse(dateFrom);
//		LocalDate to = LocalDate.parse(dateTo);
//		return ResponseEntity.ok(studentService.findStudentsByDateOfBirthRange(from, to));
//	}
//	
//	@GetMapping("/search/created-range")
//	public ResponseEntity<List<StudentResponseDTO>> findStudentsByCreationDateRange(
//			@RequestParam String dateFrom, 
//			@RequestParam String dateTo) {
//		LocalDate from = LocalDate.parse(dateFrom);
//		LocalDate to = LocalDate.parse(dateTo);
//		return ResponseEntity.ok(studentService.findStudentsByCreationDateRange(from, to));
//	}
}
