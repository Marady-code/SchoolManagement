package com.jaydee.SchoolManagement.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.service.TeacherService;

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
	
}
