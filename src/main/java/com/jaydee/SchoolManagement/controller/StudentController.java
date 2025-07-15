package com.jaydee.SchoolManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private final StudentService studentService;
	
	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody StudentRequestDTO dto){
			StudentResponseDTO create = studentService.create(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable Long studentId){
		StudentResponseDTO response = studentService.getById(studentId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllStudent(){
		List<StudentResponseDTO> response = studentService.getAll();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> update(@PathVariable Long studentId, @RequestBody StudentRequestDTO dto){
		return ResponseEntity.ok(studentService.updateStudent(studentId, dto));
	}
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<?> delete(@PathVariable Long studentId){
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
}
