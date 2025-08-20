package com.jaydee.SchoolManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.service.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;
	
	@PostMapping
	public ResponseEntity<SubjectResponseDTO> createSubject(@RequestBody SubjectRequestDTO requestDTO){
//		SubjectResponseDTO create = subjectService.createSubject(requestDTO);
		return ResponseEntity.ok(subjectService.createSubject(requestDTO));
	}
}
