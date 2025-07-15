package com.jaydee.SchoolManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;
import com.jaydee.SchoolManagement.entity.Attendance;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(target = "studentName", expression = "java(attendance.getStudent() != null ? attendance.getStudent().getLastName() + \" \" + attendance.getStudent().getFirstName() : null)")
    @Mapping(target = "className", expression = "java(attendance.getClassEntity() != null ? attendance.getClassEntity().getClassName() : null)")
    @Mapping(target = "teacherName", expression = "java(attendance.getRecordBy() != null ? attendance.getRecordBy().getLastName() + \" \" + attendance.getRecordBy().getFirstName() : null)")
    AttendanceResponseDTO toResponseDTO(Attendance attendance);

    Attendance toEntity(AttendanceRequestDTO dto);
}
