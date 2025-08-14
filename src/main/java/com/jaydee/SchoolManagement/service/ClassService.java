package com.jaydee.SchoolManagement.service;

import java.time.LocalDate;
import java.util.List;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.specification.ClassFilter;

public interface ClassService {
    ClassResponseDTO create(ClassRequestDTO dto);
    ClassResponseDTO getById(Long classId);
    List<ClassResponseDTO> getAll();
    ClassResponseDTO updateClass(Long classId, ClassRequestDTO dto);
    void deleteClass(Long classId);
    ClassResponseDTO assignTeacher(Long classId, Long teacherId);
    ClassResponseDTO assignStudent(Long classId, Long studentId);
    ClassResponseDTO removeStudentFromClass(Long classId, Long studentId);
    //ClassResponseDTO removeTeacherFromClass(Long classId, Long teacherId);
    
    // New methods using JPA Specifications
    List<ClassResponseDTO> findClassesByFilter(ClassFilter filter);
    List<ClassResponseDTO> findClassesByName(String className);
    List<ClassResponseDTO> findClassesByTeacher(String teacherName);
    List<ClassResponseDTO> findClassesByTeacherId(Long teacherId);
    List<ClassResponseDTO> findClassesByRoomNumber(String roomNumber);
    List<ClassResponseDTO> findClassesByDay(String classDay);
    List<ClassResponseDTO> findClassesByTime(String classTime);
    List<ClassResponseDTO> findClassesByStartDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<ClassResponseDTO> findClassesByStudent(String studentName);
    List<ClassResponseDTO> findClassesByStudentId(Long studentId);
    List<ClassResponseDTO> findActiveClasses();
    List<ClassResponseDTO> findClassesByCreationDateRange(LocalDate dateFrom, LocalDate dateTo);
} 