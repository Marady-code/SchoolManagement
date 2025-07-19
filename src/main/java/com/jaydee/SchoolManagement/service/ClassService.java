package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;

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
} 