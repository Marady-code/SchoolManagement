package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.TimeTable;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.repository.TimeTableRepository;
import com.jaydee.SchoolManagement.service.TimeTableService;
import com.jaydee.SchoolManagement.specification.TimeTableFilter;
import com.jaydee.SchoolManagement.specification.TimeTableSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Override
    public List<TimeTable> getAllSchedules() {
        return timeTableRepository.findAll();
    }

    @Override
    public TimeTable getScheduleById(Long scheduleId) {
        return timeTableRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFound("Schedule", scheduleId));
    }

    @Override
    public TimeTable createSchedule(TimeTable schedule) {
        return timeTableRepository.save(schedule);
    }

    @Override
    public TimeTable updateSchedule(Long scheduleId, TimeTable schedule) {
        TimeTable existing = timeTableRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFound("Schedule", scheduleId));
        
        existing.setDayOfWeek(schedule.getDayOfWeek());
        existing.setStartTime(schedule.getStartTime());
        existing.setEndTime(schedule.getEndTime());
        existing.setTeacher(schedule.getTeacher());
        existing.setClassEntity(schedule.getClassEntity());
        
        return timeTableRepository.save(existing);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        if (!timeTableRepository.existsById(scheduleId)) {
            throw new ResourceNotFound("Schedule", scheduleId);
        }
        timeTableRepository.deleteById(scheduleId);
    }

    // JPA Specification methods
    @Override
    public List<TimeTable> findSchedulesByFilter(TimeTableFilter filter) {
        TimeTableSpec spec = new TimeTableSpec(filter);
        return timeTableRepository.findAll(spec);
    }

    @Override
    public List<TimeTable> findSchedulesByDay(DayOfWeek dayOfWeek) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setDayOfWeek(dayOfWeek);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByTeacher(String teacherName) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTeacherName(teacherName);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByTeacherId(Long teacherId) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTeacherId(teacherId);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByClass(String className) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setClassName(className);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByClassId(Long classId) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setClassId(classId);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesBySubject(String subjectName) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setSubjectName(subjectName);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesBySubjectId(Long subjectId) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setSubjectId(subjectId);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByTimeRange(LocalTime startTime, LocalTime endTime) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setStartTimeFrom(startTime);
        filter.setStartTimeTo(endTime);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByTimeSlot(LocalTime timeSlot) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTimeSlot(timeSlot);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findActiveSchedules() {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setIsActive(true);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTable> findSchedulesByTimeRange(String timeRange) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTimeRange(timeRange);
        return findSchedulesByFilter(filter);
    }
} 