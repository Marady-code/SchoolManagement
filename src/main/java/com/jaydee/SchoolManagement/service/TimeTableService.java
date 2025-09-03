package com.jaydee.SchoolManagement.service;

import java.time.LocalTime;
import java.util.List;

import com.jaydee.SchoolManagement.dto.TimeTableRequestDTO;
import com.jaydee.SchoolManagement.dto.TimeTableResponseDTO;
import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.specification.TimeTableFilter;

public interface TimeTableService {
    
    // Basic CRUD operations
    //List<TimeTableResponseDTO> getAllSchedules();
    //TimeTableResponseDTO getScheduleById(Long scheduleId);
    TimeTableResponseDTO createSchedule(TimeTableRequestDTO request);
    TimeTableResponseDTO updateSchedule(Long scheduleId, TimeTableRequestDTO requestUpdateDTO);
    void deleteSchedule(Long scheduleId);
    
    // JPA Specification methods
    List<TimeTableResponseDTO> findSchedulesByFilter(TimeTableFilter filter);
    List<TimeTableResponseDTO> findSchedulesByDay(DayOfWeek dayOfWeek);
    List<TimeTableResponseDTO> findSchedulesByTeacher(String teacherName);
    List<TimeTableResponseDTO> findSchedulesByTeacherId(Long teacherId);
    List<TimeTableResponseDTO> findSchedulesByClass(String className);
    List<TimeTableResponseDTO> findSchedulesByClassId(Long classId);
    List<TimeTableResponseDTO> findSchedulesByTimeRange(LocalTime startTime, LocalTime endTime);
    List<TimeTableResponseDTO> findSchedulesByTimeRange(String timeRange); // morning, afternoon, evening, night
//  List<TimeTable> findSchedulesByTimeSlot(LocalTime timeSlot);
}