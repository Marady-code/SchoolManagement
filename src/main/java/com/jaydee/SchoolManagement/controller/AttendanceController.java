package com.jaydee.SchoolManagement.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.jaydee.SchoolManagement.dto.BulkAttendanceSubmitRequest;
import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import com.jaydee.SchoolManagement.service.AttendanceService;
import com.jaydee.SchoolManagement.specification.AttendanceFilter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {
	
    private final AttendanceService attendanceService;
    
    @PostMapping("/bulk-submit")
    public ResponseEntity<List<AttendanceResponseDTO>> submitBulkAttendance(
            @RequestBody BulkAttendanceSubmitRequest request,
            @RequestParam Long teacherId) {
        return ResponseEntity.ok(attendanceService.submitBulkAttendance(request, teacherId));
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestParam AttendanceStatus status,
            @RequestParam(required = false) String remarks,
            @RequestParam Long teacherId) {
        return ResponseEntity.ok(attendanceService.updateStatus(attendanceId, status, remarks, teacherId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AttendanceResponseDTO>> searchAttendances(
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate,
            @RequestParam(required = false) AttendanceStatus status) {
        
        AttendanceFilter filter = new AttendanceFilter();
        filter.setStudentName(studentName);
        filter.setClassName(className);
        filter.setDateFrom(fromDate);
        filter.setDateTo(toDate);
        filter.setStatus(status);
        
        return ResponseEntity.ok(attendanceService.findAttendancesByFilter(filter));
    }

    @GetMapping("/class/{classId}/today")
    public ResponseEntity<List<AttendanceResponseDTO>> getTodayAttendance(
            @PathVariable Long classId) {
        return ResponseEntity.ok(attendanceService.getTodayAttendanceForClass(classId, LocalDate.now()));
    }

    @GetMapping("/class/{classId}/date/{date}")
    public ResponseEntity<List<AttendanceResponseDTO>> getExistingAttendanceByClassAndDate(
            @PathVariable Long classId, 
            @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getExistingAttendanceByClassAndDate(classId, localDate));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentId(studentId));
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
    
    // New endpoints using JPA Specifications
    
    @PostMapping("/filter")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByFilter(@RequestBody AttendanceFilter filter) {
        return ResponseEntity.ok(attendanceService.findAttendancesByFilter(filter));
    }
    
    @GetMapping("/search/student")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByStudentName(@RequestParam String studentName) {
        return ResponseEntity.ok(attendanceService.findAttendancesByStudentName(studentName));
    }
    
    @GetMapping("/search/date-range")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByDateRange(
            @RequestParam String dateFrom, 
            @RequestParam String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        return ResponseEntity.ok(attendanceService.findAttendancesByDateRange(from, to));
    }
    
    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(attendanceService.findAttendancesByStatus(status));
    }
    
    @GetMapping("/present")
    public ResponseEntity<List<AttendanceResponseDTO>> findPresentAttendances() {
        return ResponseEntity.ok(attendanceService.findPresentAttendances());
    }
    
    @GetMapping("/absent")
    public ResponseEntity<List<AttendanceResponseDTO>> findAbsentAttendances() {
        return ResponseEntity.ok(attendanceService.findAbsentAttendances());
    }
    
    @GetMapping("/search/teacher")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByTeacherName(@RequestParam String teacherName) {
        return ResponseEntity.ok(attendanceService.findAttendancesByTeacherName(teacherName));
    }
    
    @GetMapping("/search/class-date-range")
    public ResponseEntity<List<AttendanceResponseDTO>> findAttendancesByClassAndDateRange(
            @RequestParam String className,
            @RequestParam String dateFrom, 
            @RequestParam String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        return ResponseEntity.ok(attendanceService.findAttendancesByClassAndDateRange(className, from, to));
    }
} 