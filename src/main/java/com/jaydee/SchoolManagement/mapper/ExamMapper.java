package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.ExamRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResponseDTO;
import com.jaydee.SchoolManagement.entity.Exam;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mapping(target = "subject.subjectId", source = "subjectId")
    @Mapping(target = "classEntity.classId", source = "classId")
    Exam toEntity(ExamRequestDTO dto);

    @Mapping(target = "subjectName", source = "subject.subjectName")
    @Mapping(target = "className", source = "classEntity.className")
    ExamResponseDTO toResponseDTO(Exam entity);

    List<ExamResponseDTO> toDtoList(List<Exam> exams);
} 