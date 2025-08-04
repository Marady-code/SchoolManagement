package com.jaydee.SchoolManagement.service;

import java.time.LocalTime;
import java.util.List;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.TimeTable;
import com.jaydee.SchoolManagement.specification.TimeTableFilter;

public interface TimeTableService {
    
    // Basic CRUD operations
    List<TimeTable> getAllSchedules();
    TimeTable getScheduleById(Long scheduleId);
    TimeTable createSchedule(TimeTable schedule);
    TimeTable updateSchedule(Long scheduleId, TimeTable schedule);
    void deleteSchedule(Long scheduleId);
    
    // JPA Specification methods
    List<TimeTable> findSchedulesByFilter(TimeTableFilter filter);
    List<TimeTable> findSchedulesByDay(DayOfWeek dayOfWeek);
    List<TimeTable> findSchedulesByTeacher(String teacherName);
    List<TimeTable> findSchedulesByTeacherId(Long teacherId);
    List<TimeTable> findSchedulesByClass(String className);
    List<TimeTable> findSchedulesByClassId(Long classId);
    List<TimeTable> findSchedulesByTimeRange(LocalTime startTime, LocalTime endTime);
    List<TimeTable> findSchedulesByTimeSlot(LocalTime timeSlot);
    List<TimeTable> findActiveSchedules();
    List<TimeTable> findSchedulesByTimeRange(String timeRange); // morning, afternoon, evening, night
} 