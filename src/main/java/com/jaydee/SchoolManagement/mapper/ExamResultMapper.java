package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.ExamResultRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResultResponseDTO;
import com.jaydee.SchoolManagement.entity.ExamResult;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {

    @Mapping(target = "exam.examId", source = "examId")
    @Mapping(target = "student.studentId", source = "studentId")
    ExamResult toEntity(ExamResultRequestDTO dto);

    @Mapping(target = "examTitle", source = "exam.examTitle")
    @Mapping(target = "studentName", expression = "java(entity.getStudent().getLastName() + \" \" + entity.getStudent().getFirstName())")
    @Mapping(target = "subjectName", source = "exam.subject.subjectName")
    ExamResultResponseDTO toResponseDTO(ExamResult entity);

    List<ExamResultResponseDTO> toDtoList(List<ExamResult> examResults);
} 