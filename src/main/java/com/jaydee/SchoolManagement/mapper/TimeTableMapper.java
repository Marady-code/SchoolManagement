package com.jaydee.SchoolManagement.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.jaydee.SchoolManagement.dto.TimeTableRequestDTO;
import com.jaydee.SchoolManagement.dto.TimeTableResponseDTO;
import com.jaydee.SchoolManagement.entity.TimeTable;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TimeTableMapper {

	@Mapping(target = "teacher.teacherId", source = "teacherId")
	@Mapping(target = "classEntity.classId", source = "classId")
	TimeTable toEntity(TimeTableRequestDTO requestDTO);
	
	//@Mapping(target = "teacherName", expression = "java(entity.getTeacher().getLastName() + \" \" + entity.getTeacher().getFirstName())")
	@Mapping(target = "teacherName", expression = "java(timeTable.getTeacher().getLastName() + \" \" + timeTable.getTeacher().getFirstName())")
	@Mapping(target = "room", source = "classEntity.roomNumber")
	@Mapping(target = "subjectName", source = "subject.subjectName")
	@Mapping(target = "className", source = "classEntity.className")
	TimeTableResponseDTO toResponseDTO(TimeTable timeTable);
	
	List<TimeTableResponseDTO> toResponseDTOs(List<TimeTable> timeTables);
	
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTimeTableFromDto(TimeTableRequestDTO dto, @MappingTarget TimeTable timeTable);
}
