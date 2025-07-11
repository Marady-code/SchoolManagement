package com.jaydee.SchoolManagement.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
		try {
			//Teacher teacher = teacherMapper.toEntity(dto);
			TeacherResponseDTO response = teacherService.create(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
		}
	}
	
}
