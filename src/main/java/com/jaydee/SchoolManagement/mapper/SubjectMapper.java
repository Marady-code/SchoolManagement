package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jaydee.SchoolManagement.dto.SubjectRequestDTO;
import com.jaydee.SchoolManagement.dto.SubjectResponseDTO;
import com.jaydee.SchoolManagement.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectRequestDTO dto);

    SubjectResponseDTO toResponseDTO(Subject entity);

    List<SubjectResponseDTO> toDtoList(List<Subject> subjects);
} 