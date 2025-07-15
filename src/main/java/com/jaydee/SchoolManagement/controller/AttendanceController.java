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

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;
import com.jaydee.SchoolManagement.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {
	
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.markAttendance(dto));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentId(studentId));
    }

    @GetMapping("/student/name/{name}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByStudentName(@PathVariable String name) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentName(name));
    }

    @GetMapping("/class/{className}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByClassName(@PathVariable String className) {
        return ResponseEntity.ok(attendanceService.getAttendanceByClassName(className));
    }

    @GetMapping("/class/{className}/date")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByClassNameAndDate(@PathVariable String className, @RequestParam("date") String date) {
        java.time.LocalDate localDate = java.time.LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAttendanceByClassNameAndDate(className, localDate));
    }

    @PutMapping("/{id}")
    public AttendanceResponseDTO updateAttendance(@PathVariable Long id, @RequestBody AttendanceRequestDTO requestDTO) {
        return attendanceService.updateAttendance(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
} 