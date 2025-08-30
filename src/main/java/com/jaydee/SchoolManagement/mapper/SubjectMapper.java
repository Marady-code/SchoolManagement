package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.entity.Subject;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapper {

	Subject toEntity(SubjectRequestDTO requestDTO);
	
	SubjectResponseDTO toResponseDTO(Subject subject);
	
	List<SubjectResponseDTO> toDtoList(List<Subject> subjects);
}
