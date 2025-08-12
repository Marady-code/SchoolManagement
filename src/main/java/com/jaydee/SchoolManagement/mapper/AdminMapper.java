package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.AdminRequestDTO;
import com.jaydee.SchoolManagement.dto.AdminResponseDTO;
import com.jaydee.SchoolManagement.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toEntity(AdminRequestDTO dto);

    @Mapping(target = "fullName", expression = "java(entity.getLastName() + \" \" + entity.getFirstName())")
    AdminResponseDTO toResponseDTO(Admin entity);

    List<AdminResponseDTO> toDtoList(List<Admin> admins);
} 