package com.jaydee.SchoolManagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;

public interface AttendanceService {
    AttendanceResponseDTO markAttendance(AttendanceRequestDTO requestDTO);
//    AttendanceResponseDTO getAttendanceById(Long id);
//    List<AttendanceResponseDTO> getAllAttendances();
    List<AttendanceResponseDTO> getAttendanceByStudentId(Long studentId);
    List<AttendanceResponseDTO> getAttendanceByStudentName(String name);
    List<AttendanceResponseDTO> getAttendanceByClassName(String className);
    List<AttendanceResponseDTO> getAttendanceByClassNameAndDate(String className, LocalDate date);
//    List<AttendanceResponseDTO> findAll(Specification<Attendance> spec);
    AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO);
    void deleteAttendance(Long id);
} 