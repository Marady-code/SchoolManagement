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

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.service.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;

    @PostMapping
    public ResponseEntity<ClassResponseDTO> createClass(@RequestBody ClassRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.create(dto));
    }

    @GetMapping("/{classId}")
    public ResponseEntity<ClassResponseDTO> getClassById(@PathVariable Long classId) {
        return ResponseEntity.ok(classService.getById(classId));
    }

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> getAllClasses() {
        return ResponseEntity.ok(classService.getAll());
    }

    @PutMapping("/{classId}")
    public ResponseEntity<ClassResponseDTO> updateClass(@PathVariable Long classId, @RequestBody ClassRequestDTO dto) {
        return ResponseEntity.ok(classService.updateClass(classId, dto));
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{classId}/assign-teacher/{teacherId}")
    public ResponseEntity<ClassResponseDTO> assignTeacher(@PathVariable Long classId, @PathVariable Long teacherId) {
        return ResponseEntity.ok(classService.assignTeacher(classId, teacherId));
    }

    @PostMapping("/{classId}/assign-student/{studentId}")
    public ResponseEntity<ClassResponseDTO> assignStudent(@PathVariable Long classId, @PathVariable Long studentId) {
        return ResponseEntity.ok(classService.assignStudent(classId, studentId));
    }
    
    @DeleteMapping("/{classId}/remove-student/{studentId}")
    public ResponseEntity<ClassResponseDTO> removeStudentFromClass(@PathVariable Long classId,@PathVariable Long studentId){
    	return ResponseEntity.ok(classService.removeStudentFromClass(classId, studentId));
    }
} 