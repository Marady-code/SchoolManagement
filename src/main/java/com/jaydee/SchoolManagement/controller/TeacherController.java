package com.jaydee.SchoolManagement.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.GenderEnum;
import com.jaydee.SchoolManagement.service.TeacherService;
import com.jaydee.SchoolManagement.specification.TeacherFilter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
	
	private final TeacherService teacherService;
	//private final TeacherMapper teacherMapper;
	
	@PostMapping
	public ResponseEntity<?> createTeacher(@RequestBody TeacherRequestDTO dto){
			TeacherResponseDTO create = teacherService.create(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<?> getTeacherById(@PathVariable Long teacherId){
		TeacherResponseDTO response = teacherService.getById(teacherId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTeacher(){
		List<TeacherResponseDTO> response = teacherService.getAll();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{teacherId}")
	public ResponseEntity<?> update(@PathVariable Long teacherId, @RequestBody TeacherRequestDTO dto){
		return ResponseEntity.ok(teacherService.updateTeacher(teacherId, dto));
	}
	
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<?> delete(@PathVariable Long teacherId){
		teacherService.deleteTeacher(teacherId);
		return ResponseEntity.noContent().build();
	}
	
	// New endpoints using JPA Specifications
	
	@PostMapping("/filter")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByFilter(@RequestBody TeacherFilter filter) {
		return ResponseEntity.ok(teacherService.findTeachersByFilter(filter));
	}
	
	@GetMapping("/search/name")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByName(@RequestParam String name) {
		return ResponseEntity.ok(teacherService.findTeachersByName(name));
	}
	
	@GetMapping("/search/fullname")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByFullName(@RequestParam String fullName) {
		return ResponseEntity.ok(teacherService.findTeachersByFullName(fullName));
	}
	
	@GetMapping("/search/gender/{gender}")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByGender(@PathVariable String gender) {
		GenderEnum genderEnum = GenderEnum.valueOf(gender.toUpperCase());
		return ResponseEntity.ok(teacherService.findTeachersByGender(genderEnum));
	}
	
	@GetMapping("/search/qualification")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByQualification(@RequestParam String qualification) {
		return ResponseEntity.ok(teacherService.findTeachersByQualification(qualification));
	}
	
	@GetMapping("/search/salary-range")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersBySalaryRange(
			@RequestParam Long salaryFrom, 
			@RequestParam Long salaryTo) {
		return ResponseEntity.ok(teacherService.findTeachersBySalaryRange(salaryFrom, salaryTo));
	}
	
	@GetMapping("/search/experience-range")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByExperienceRange(
			@RequestParam Integer experienceFrom, 
			@RequestParam Integer experienceTo) {
		return ResponseEntity.ok(teacherService.findTeachersByExperienceRange(experienceFrom, experienceTo));
	}
	
	@GetMapping("/search/joining-date-range")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByJoiningDateRange(
			@RequestParam String dateFrom, 
			@RequestParam String dateTo) {
		LocalDate from = LocalDate.parse(dateFrom);
		LocalDate to = LocalDate.parse(dateTo);
		return ResponseEntity.ok(teacherService.findTeachersByJoiningDateRange(from, to));
	}
	
	@GetMapping("/search/age-range")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByAgeRange(
			@RequestParam Integer ageFrom, 
			@RequestParam Integer ageTo) {
		return ResponseEntity.ok(teacherService.findTeachersByAgeRange(ageFrom, ageTo));
	}
	
	@GetMapping("/search/class")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByClass(@RequestParam String className) {
		return ResponseEntity.ok(teacherService.findTeachersByClass(className));
	}
	
	@GetMapping("/search/student")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByStudent(@RequestParam String studentName) {
		return ResponseEntity.ok(teacherService.findTeachersByStudent(studentName));
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<TeacherResponseDTO>> findActiveTeachers() {
		return ResponseEntity.ok(teacherService.findActiveTeachers());
	}
	
	@GetMapping("/search/place")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByPlace(@RequestParam String place) {
		return ResponseEntity.ok(teacherService.findTeachersByPlace(place));
	}
	
	@GetMapping("/search/phone")
	public ResponseEntity<List<TeacherResponseDTO>> findTeachersByPhoneNumber(@RequestParam String phoneNumber) {
		return ResponseEntity.ok(teacherService.findTeachersByPhoneNumber(phoneNumber));
	}
}
