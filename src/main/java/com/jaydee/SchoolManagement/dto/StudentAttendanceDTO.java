package com.jaydee.SchoolManagement.dto;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import lombok.Data;

@Data
public class StudentAttendanceDTO {
    private Long studentId;
    private String studentName;
    private AttendanceStatus status = AttendanceStatus.PRESENT; // Default to PRESENT
    private String remark;
}
