package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jaydee.SchoolManagement.dto.ClassStudentsResponseDTO;
import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.Student;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    Student toEntity(StudentRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
    StudentResponseDTO toResponseDTO(Student entity);

    List<StudentResponseDTO> toDtoList(List<Student> students);
    
    @Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
    ClassStudentsResponseDTO classStudentsResponseDTO(Student entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentRequestDTO dto, @MappingTarget Student student);
}
