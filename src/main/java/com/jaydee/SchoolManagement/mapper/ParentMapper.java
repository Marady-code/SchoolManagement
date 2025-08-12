package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.ParentRequestDTO;
import com.jaydee.SchoolManagement.dto.ParentResponseDTO;
import com.jaydee.SchoolManagement.entity.Parent;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    Parent toEntity(ParentRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
    ParentResponseDTO toResponseDTO(Parent entity);

    List<ParentResponseDTO> toDtoList(List<Parent> parents);
} 