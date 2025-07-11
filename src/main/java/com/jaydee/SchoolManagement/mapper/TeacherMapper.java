package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jaydee.SchoolManagement.dto.TeacherRequestDTO;
import com.jaydee.SchoolManagement.dto.TeacherResponseDTO;
import com.jaydee.SchoolManagement.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	//TeacherRequestDTO toRequestDTO(Teacher teacher);
	
	Teacher toEntity(TeacherRequestDTO dto);
	
	TeacherResponseDTO toResponseDTO(Teacher entity);
	
	List<TeacherResponseDTO> toDtoList(List<Teacher> teachers);
}
