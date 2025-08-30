package com.jaydee.SchoolManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.service.SubjectService;
import com.jaydee.SchoolManagement.specification.SubjectFilter;

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
	
	@PutMapping("/{subjectId}")
	public ResponseEntity<SubjectResponseDTO> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectRequestDTO requestDTO){
		SubjectResponseDTO response = subjectService.updateSubject(subjectId, requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects(){
		List<SubjectResponseDTO> response = subjectService.getAllSubjects();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<SubjectResponseDTO>> findStudentByFilter(@RequestBody SubjectFilter filter){
		return ResponseEntity.ok(subjectService.findSubjectsByFilter(filter));
	}
	
	@GetMapping("/search/name")
	public ResponseEntity<List<SubjectResponseDTO>> findBySubjectName(@RequestParam String subjectName){
		List<SubjectResponseDTO> response = subjectService.findBySubjectName(subjectName);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{subjectId}")
	public ResponseEntity<SubjectResponseDTO> getById(@PathVariable Long subjectId){
		SubjectResponseDTO response = subjectService.getSubjectById(subjectId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/search/subjectCode")
	public ResponseEntity<List<SubjectResponseDTO>> getByCode(@RequestParam String code){
		List<SubjectResponseDTO> response = subjectService.findBySubjectCode(code);
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
