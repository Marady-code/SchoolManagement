package com.jaydee.SchoolManagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;
import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import com.jaydee.SchoolManagement.specification.AttendanceFilter;

public interface AttendanceService {
    AttendanceResponseDTO markAttendance(AttendanceRequestDTO requestDTO);
	AttendanceResponseDTO updateStudentAttendance(Long attendanceId, AttendanceStatus status, String remarks);
	
	List<AttendanceResponseDTO> getExistingAttendanceByClassAndDate(Long classId, LocalDate date);
	
    List<AttendanceResponseDTO> getAttendanceByStudentId(Long studentId);
    List<AttendanceResponseDTO> getAttendanceByClassName(String className);
    List<AttendanceResponseDTO> getAttendanceByClassNameAndDate(String className, LocalDate date);
    //AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO);
    
    void deleteAttendance(Long id);
    List<AttendanceResponseDTO> getAttendanceByStudentFullName(String fullName);
    
    List<AttendanceResponseDTO> getTodayAttendanceForClass(Long classId, LocalDate date);
    AttendanceResponseDTO updateStatus(Long attendanceId, AttendanceStatus status, String remarks, Long teacherId);
    
    // New methods using JPA Specifications
    List<AttendanceResponseDTO> findAttendancesByFilter(AttendanceFilter filter);
    List<AttendanceResponseDTO> findAttendancesByStudentName(String studentName);
    List<AttendanceResponseDTO> findAttendancesByDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<AttendanceResponseDTO> findAttendancesByStatus(String status);
    List<AttendanceResponseDTO> findPresentAttendances();
    List<AttendanceResponseDTO> findAbsentAttendances();
    List<AttendanceResponseDTO> findAttendancesByTeacherName(String teacherName);
    List<AttendanceResponseDTO> findAttendancesByClassAndDateRange(String className, LocalDate dateFrom, LocalDate dateTo);
} 