package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.TimeTableRequestDTO;
import com.jaydee.SchoolManagement.dto.TimeTableResponseDTO;
import com.jaydee.SchoolManagement.entity.TimeTable;

@Mapper(componentModel = "spring")
public interface TimeTableMapper {

    @Mapping(target = "teacher.teacherId", source = "teacherId")
    @Mapping(target = "subject.subjectId", source = "subjectId")
    @Mapping(target = "classEntity.classId", source = "classId")
    TimeTable toEntity(TimeTableRequestDTO dto);

    @Mapping(target = "teacherName", expression = "java(entity.getTeacher().getLastName() + \" \" + entity.getTeacher().getFirstName())")
    @Mapping(target = "subjectName", source = "subject.subjectName")
    @Mapping(target = "className", source = "classEntity.className")
    TimeTableResponseDTO toResponseDTO(TimeTable entity);

    List<TimeTableResponseDTO> toDtoList(List<TimeTable> timeTables);
} 