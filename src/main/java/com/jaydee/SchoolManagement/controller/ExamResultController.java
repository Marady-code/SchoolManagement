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

import com.jaydee.SchoolManagement.dto.ExamResultRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResultResponseDTO;
import com.jaydee.SchoolManagement.service.ExamResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exam-results")
@RequiredArgsConstructor
public class ExamResultController {
    
    private final ExamResultService examResultService;
    
    @PostMapping
    public ResponseEntity<ExamResultResponseDTO> createExamResult(@RequestBody ExamResultRequestDTO requestDTO) {
        ExamResultResponseDTO response = examResultService.createExamResult(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExamResultResponseDTO> updateExamResult(@PathVariable Long id, @RequestBody ExamResultRequestDTO requestDTO) {
        ExamResultResponseDTO response = examResultService.updateExamResult(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamResult(@PathVariable Long id) {
        examResultService.deleteExamResult(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExamResultResponseDTO> getExamResultById(@PathVariable Long id) {
        ExamResultResponseDTO response = examResultService.getExamResultById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<ExamResultResponseDTO>> getAllExamResults() {
        List<ExamResultResponseDTO> response = examResultService.getAllExamResults();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResultResponseDTO>> findExamResultsByStudent(@PathVariable Long studentId) {
        List<ExamResultResponseDTO> response = examResultService.findExamResultsByStudent(studentId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResultResponseDTO>> findExamResultsByExam(@PathVariable Long examId) {
        List<ExamResultResponseDTO> response = examResultService.findExamResultsByExam(examId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<ExamResultResponseDTO>> findExamResultsBySubject(@PathVariable Long subjectId) {
        List<ExamResultResponseDTO> response = examResultService.findExamResultsBySubject(subjectId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/grade")
    public ResponseEntity<List<ExamResultResponseDTO>> findExamResultsByGrade(@RequestParam String grade) {
        List<ExamResultResponseDTO> response = examResultService.findExamResultsByGrade(grade);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/percentage-range")
    public ResponseEntity<List<ExamResultResponseDTO>> findExamResultsByPercentageRange(
            @RequestParam(required = false) Double minPercentage,
            @RequestParam(required = false) Double maxPercentage) {
        List<ExamResultResponseDTO> response = examResultService.findExamResultsByPercentageRange(minPercentage, maxPercentage);
        return ResponseEntity.ok(response);
    }
} 