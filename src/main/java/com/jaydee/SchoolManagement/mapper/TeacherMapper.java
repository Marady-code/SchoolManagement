package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	//TeacherRequestDTO toRequestDTO(Teacher teacher);
	
	Teacher toEntity(TeacherRequestDTO dto);
	
	@Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
	TeacherResponseDTO toResponseDTO(Teacher entity);
	
	List<TeacherResponseDTO> toDtoList(List<Teacher> teachers);
}
