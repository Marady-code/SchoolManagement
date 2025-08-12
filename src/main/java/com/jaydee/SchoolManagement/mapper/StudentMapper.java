package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.StudentRequestDTO;
import com.jaydee.SchoolManagement.dto.StudentResponseDTO;
import com.jaydee.SchoolManagement.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
    @Mapping(target = "parentName", expression = "java(entity.getParent() != null ? entity.getParent().getLastName() + \" \" + entity.getParent().getFirstName() : null)")
    StudentResponseDTO toResponseDTO(Student entity);

    List<StudentResponseDTO> toDtoList(List<Student> students);
}
