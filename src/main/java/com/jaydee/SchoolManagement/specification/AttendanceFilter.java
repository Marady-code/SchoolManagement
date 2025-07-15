package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceFilter {
    private Long studentId;
    private Long classId;
    private Long teacherId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private AttendanceStatus status;
} 