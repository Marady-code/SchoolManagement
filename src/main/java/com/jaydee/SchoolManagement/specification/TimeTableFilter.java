package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import lombok.Data;
import java.time.LocalTime;

@Data
public class TimeTableFilter {
    private Long scheduleId; // For specific schedule ID
    private DayOfWeek dayOfWeek; // For specific day filter
    private LocalTime startTimeFrom; // For start time range
    private LocalTime startTimeTo; // For start time range
    private LocalTime endTimeFrom; // For end time range
    private LocalTime endTimeTo; // For end time range
    private Long teacherId; // For schedules of specific teacher
    private String teacherName; // For schedules of teacher with specific name
    private Long classId; // For schedules of specific class
    private String className; // For schedules of classes with specific name
    private LocalTime timeSlot; // For schedules at specific time
    private Boolean isActive; // For active schedules (current time within schedule)
    private Boolean isOverlapping; // For overlapping schedules
    private String timeRange; // For time range search (e.g., "morning", "afternoon", "evening")
} 