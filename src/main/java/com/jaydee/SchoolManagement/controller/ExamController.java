package com.jaydee.SchoolManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.jaydee.SchoolManagement.dto.ExamRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResponseDTO;
import com.jaydee.SchoolManagement.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {
    
    private final ExamService examService;
    
    @PostMapping
    public ResponseEntity<ExamResponseDTO> createExam(@RequestBody ExamRequestDTO requestDTO) {
        ExamResponseDTO response = examService.createExam(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> updateExam(@PathVariable Long id, @RequestBody ExamRequestDTO requestDTO) {
        ExamResponseDTO response = examService.updateExam(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> getExamById(@PathVariable Long id) {
        ExamResponseDTO response = examService.getExamById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<ExamResponseDTO>> getAllExams() {
        List<ExamResponseDTO> response = examService.getAllExams();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<ExamResponseDTO>> findExamsBySubject(@PathVariable Long subjectId) {
        List<ExamResponseDTO> response = examService.findExamsBySubject(subjectId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ExamResponseDTO>> findExamsByClass(@PathVariable Long classId) {
        List<ExamResponseDTO> response = examService.findExamsByClass(classId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/date")
    public ResponseEntity<List<ExamResponseDTO>> findExamsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate examDate) {
        List<ExamResponseDTO> response = examService.findExamsByDate(examDate);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<ExamResponseDTO>> findExamsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        List<ExamResponseDTO> response = examService.findExamsByDateRange(dateFrom, dateTo);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/type")
    public ResponseEntity<List<ExamResponseDTO>> findExamsByType(@RequestParam String examType) {
        List<ExamResponseDTO> response = examService.findExamsByType(examType);
        return ResponseEntity.ok(response);
    }
} 