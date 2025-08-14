package com.jaydee.SchoolManagement.dto;

import java.time.LocalDate;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import lombok.Data;

@Data
public class AttendanceResponseDTO {
	
    private Long attendanceId;
    private LocalDate date;
    private AttendanceStatus status;
    private String remark;
    private Long studentId;
    private String studentName;
    private Long classId;
    private String className;
    private Long teacherId;
    private String teacherName;
}
