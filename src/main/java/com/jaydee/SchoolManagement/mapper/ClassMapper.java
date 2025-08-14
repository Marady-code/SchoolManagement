package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.entity.ClassEntity;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class})
public interface ClassMapper {

    @Mapping(target = "createAt", ignore = true)
    ClassEntity toEntity(ClassRequestDTO dto);

    ClassResponseDTO toResponseDTO(ClassEntity entity);

    List<ClassResponseDTO> toDtoList(List<ClassEntity> entities);
}
