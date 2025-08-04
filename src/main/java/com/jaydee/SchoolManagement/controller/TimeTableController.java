package com.jaydee.SchoolManagement.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.TimeTable;
import com.jaydee.SchoolManagement.service.TimeTableService;
import com.jaydee.SchoolManagement.specification.TimeTableFilter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class TimeTableController {

    private final TimeTableService timeTableService;

    // Basic CRUD operations
    @GetMapping
    public ResponseEntity<List<TimeTable>> getAllSchedules() {
        return ResponseEntity.ok(timeTableService.getAllSchedules());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<TimeTable> getScheduleById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(timeTableService.getScheduleById(scheduleId));
    }

    @PostMapping
    public ResponseEntity<TimeTable> createSchedule(@RequestBody TimeTable schedule) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeTableService.createSchedule(schedule));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<TimeTable> updateSchedule(@PathVariable Long scheduleId, @RequestBody TimeTable schedule) {
        return ResponseEntity.ok(timeTableService.updateSchedule(scheduleId, schedule));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        timeTableService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    // JPA Specification endpoints
    @PostMapping("/filter")
    public ResponseEntity<List<TimeTable>> findSchedulesByFilter(@RequestBody TimeTableFilter filter) {
        return ResponseEntity.ok(timeTableService.findSchedulesByFilter(filter));
    }

    @GetMapping("/search/day/{dayOfWeek}")
    public ResponseEntity<List<TimeTable>> findSchedulesByDay(@PathVariable String dayOfWeek) {
        DayOfWeek day = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        return ResponseEntity.ok(timeTableService.findSchedulesByDay(day));
    }

    @GetMapping("/search/teacher")
    public ResponseEntity<List<TimeTable>> findSchedulesByTeacher(@RequestParam String teacherName) {
        return ResponseEntity.ok(timeTableService.findSchedulesByTeacher(teacherName));
    }

    @GetMapping("/search/teacher/{teacherId}")
    public ResponseEntity<List<TimeTable>> findSchedulesByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(timeTableService.findSchedulesByTeacherId(teacherId));
    }

    @GetMapping("/search/class")
    public ResponseEntity<List<TimeTable>> findSchedulesByClass(@RequestParam String className) {
        return ResponseEntity.ok(timeTableService.findSchedulesByClass(className));
    }

    @GetMapping("/search/class/{classId}")
    public ResponseEntity<List<TimeTable>> findSchedulesByClassId(@PathVariable Long classId) {
        return ResponseEntity.ok(timeTableService.findSchedulesByClassId(classId));
    }

    @GetMapping("/search/time-range")
    public ResponseEntity<List<TimeTable>> findSchedulesByTimeRange(
            @RequestParam String startTime, 
            @RequestParam String endTime) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        return ResponseEntity.ok(timeTableService.findSchedulesByTimeRange(start, end));
    }

    @GetMapping("/search/time-slot")
    public ResponseEntity<List<TimeTable>> findSchedulesByTimeSlot(@RequestParam String timeSlot) {
        LocalTime time = LocalTime.parse(timeSlot);
        return ResponseEntity.ok(timeTableService.findSchedulesByTimeSlot(time));
    }

    @GetMapping("/active")
    public ResponseEntity<List<TimeTable>> findActiveSchedules() {
        return ResponseEntity.ok(timeTableService.findActiveSchedules());
    }

    @GetMapping("/search/time-range-category")
    public ResponseEntity<List<TimeTable>> findSchedulesByTimeRangeCategory(@RequestParam String timeRange) {
        return ResponseEntity.ok(timeTableService.findSchedulesByTimeRange(timeRange));
    }
} 