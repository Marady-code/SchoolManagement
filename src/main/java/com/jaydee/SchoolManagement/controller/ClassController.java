package com.jaydee.SchoolManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.service.ClassService;
import com.jaydee.SchoolManagement.specification.ClassFilter;

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

    @PatchMapping("/{classId}")
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
    
    // New endpoints using JPA Specifications
    
    @PostMapping("/filter")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByFilter(@RequestBody ClassFilter filter) {
        return ResponseEntity.ok(classService.findClassesByFilter(filter));
    }
    
    @GetMapping("/search/name")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByName(@RequestParam String className) {
        return ResponseEntity.ok(classService.findClassesByName(className));
    }
    
    @GetMapping("/search/teacher")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByTeacher(@RequestParam String teacherName) {
        return ResponseEntity.ok(classService.findClassesByTeacher(teacherName));
    }
    
    @GetMapping("/search/teacher/{teacherId}")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(classService.findClassesByTeacherId(teacherId));
    }
    
    @GetMapping("/search/room")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByRoomNumber(@RequestParam String roomNumber) {
        return ResponseEntity.ok(classService.findClassesByRoomNumber(roomNumber));
    }
    
    @GetMapping("/search/day")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByDay(@RequestParam String classDay) {
        return ResponseEntity.ok(classService.findClassesByDay(classDay));
    }
    
    @GetMapping("/search/time")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByTime(@RequestParam String classTime) {
        return ResponseEntity.ok(classService.findClassesByTime(classTime));
    }
    
    @GetMapping("/search/start-date-range")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByStartDateRange(
            @RequestParam String dateFrom, 
            @RequestParam String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        return ResponseEntity.ok(classService.findClassesByStartDateRange(from, to));
    }
    
    @GetMapping("/search/student")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByStudent(@RequestParam String studentName) {
        return ResponseEntity.ok(classService.findClassesByStudent(studentName));
    }
    
    @GetMapping("/search/student/{studentId}")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(classService.findClassesByStudentId(studentId));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<ClassResponseDTO>> findActiveClasses() {
        return ResponseEntity.ok(classService.findActiveClasses());
    }
    
    @GetMapping("/search/created-range")
    public ResponseEntity<List<ClassResponseDTO>> findClassesByCreationDateRange(
            @RequestParam String dateFrom, 
            @RequestParam String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        return ResponseEntity.ok(classService.findClassesByCreationDateRange(from, to));
    }
} 