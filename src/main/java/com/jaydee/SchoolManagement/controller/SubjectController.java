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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<SubjectResponseDTO> createSubject(@RequestBody SubjectRequestDTO requestDTO) {
        SubjectResponseDTO response = subjectService.createSubject(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectRequestDTO requestDTO) {
        SubjectResponseDTO response = subjectService.updateSubject(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable Long id) {
        SubjectResponseDTO response = subjectService.getSubjectById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        List<SubjectResponseDTO> response = subjectService.getAllSubjects();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<SubjectResponseDTO>> findSubjectsByName(@RequestParam String subjectName) {
        List<SubjectResponseDTO> response = subjectService.findSubjectsByName(subjectName);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search/code")
    public ResponseEntity<List<SubjectResponseDTO>> findSubjectsByCode(@RequestParam String subjectCode) {
        List<SubjectResponseDTO> response = subjectService.findSubjectsByCode(subjectCode);
        return ResponseEntity.ok(response);
    }
} 