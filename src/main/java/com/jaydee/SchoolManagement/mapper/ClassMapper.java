package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.entity.ClassEntity;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassMapper {

    @Mapping(target = "createAt", ignore = true)
    ClassEntity toEntity(ClassRequestDTO dto);

    ClassResponseDTO toResponseDTO(ClassEntity entity);
    
    List<ClassResponseDTO> toDtoList(List<ClassEntity> entities);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClassFromDto(ClassRequestDTO dto, @MappingTarget ClassEntity entity);
    
}
