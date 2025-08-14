package com.jaydee.SchoolManagement.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.TimeTable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import java.time.LocalTime;

@Data
public class TimeTableSpec implements Specification<TimeTable> {

    private final TimeTableFilter timeTableFilter;
    
    public TimeTableSpec(TimeTableFilter timeTableFilter) {
        this.timeTableFilter = timeTableFilter;
    }
    
    @Override
    public Predicate toPredicate(Root<TimeTable> timeTable, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        // Schedule ID filter
        if (timeTableFilter.getScheduleId() != null) {
            predicates.add(cb.equal(timeTable.get("schedule_id"), timeTableFilter.getScheduleId()));
        }
        
        // Day of week filter
        if (timeTableFilter.getDayOfWeek() != null) {
            predicates.add(cb.equal(timeTable.get("dayOfWeek"), timeTableFilter.getDayOfWeek()));
        }
        
        // Start time range filters
        if (timeTableFilter.getStartTimeFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(timeTable.get("startTime"), timeTableFilter.getStartTimeFrom()));
        }
        if (timeTableFilter.getStartTimeTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(timeTable.get("startTime"), timeTableFilter.getStartTimeTo()));
        }
        
        // End time range filters
        if (timeTableFilter.getEndTimeFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(timeTable.get("endTime"), timeTableFilter.getEndTimeFrom()));
        }
        if (timeTableFilter.getEndTimeTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(timeTable.get("endTime"), timeTableFilter.getEndTimeTo()));
        }
        
        // Teacher ID filter
        if (timeTableFilter.getTeacherId() != null) {
            predicates.add(cb.equal(timeTable.get("teacher").get("teacherId"), timeTableFilter.getTeacherId()));
        }
        
        // Teacher name filter (case-insensitive)
        if (timeTableFilter.getTeacherName() != null && !timeTableFilter.getTeacherName().trim().isEmpty()) {
            String searchTerm = "%" + timeTableFilter.getTeacherName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(timeTable.get("teacher").get("firstName")), searchTerm),
                cb.like(cb.lower(timeTable.get("teacher").get("lastName")), searchTerm)
            ));
        }
        
        // Class ID filter
        if (timeTableFilter.getClassId() != null) {
            predicates.add(cb.equal(timeTable.get("classEntity").get("classId"), timeTableFilter.getClassId()));
        }
        
        // Class name filter (case-insensitive)
        if (timeTableFilter.getClassName() != null && !timeTableFilter.getClassName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(timeTable.get("classEntity").get("className")), 
                "%" + timeTableFilter.getClassName().toLowerCase() + "%"));
        }
        
        // Time slot filter (schedules at specific time)
        if (timeTableFilter.getTimeSlot() != null) {
            LocalTime timeSlot = timeTableFilter.getTimeSlot();
            predicates.add(cb.and(
                cb.lessThanOrEqualTo(timeTable.get("startTime"), timeSlot),
                cb.greaterThan(timeTable.get("endTime"), timeSlot)
            ));
        }
        
        // Active schedules filter (current time within schedule)
        if (timeTableFilter.getIsActive() != null) {
            LocalTime currentTime = LocalTime.now();
            if (timeTableFilter.getIsActive()) {
                // Active: current time is within the schedule time
                predicates.add(cb.and(
                    cb.lessThanOrEqualTo(timeTable.get("startTime"), currentTime),
                    cb.greaterThan(timeTable.get("endTime"), currentTime)
                ));
            } else {
                // Inactive: current time is outside the schedule time
                predicates.add(cb.or(
                    cb.greaterThan(timeTable.get("startTime"), currentTime),
                    cb.lessThanOrEqualTo(timeTable.get("endTime"), currentTime)
                ));
            }
        }
        
        // Time range filter (morning, afternoon, evening)
        if (timeTableFilter.getTimeRange() != null && !timeTableFilter.getTimeRange().trim().isEmpty()) {
            String timeRange = timeTableFilter.getTimeRange().toLowerCase();
            switch (timeRange) {
                case "morning":
                    predicates.add(cb.and(
                        cb.greaterThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(6, 0)),
                        cb.lessThan(timeTable.get("startTime"), LocalTime.of(12, 0))
                    ));
                    break;
                case "afternoon":
                    predicates.add(cb.and(
                        cb.greaterThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(12, 0)),
                        cb.lessThan(timeTable.get("startTime"), LocalTime.of(17, 0))
                    ));
                    break;
                case "evening":
                    predicates.add(cb.and(
                        cb.greaterThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(17, 0)),
                        cb.lessThan(timeTable.get("startTime"), LocalTime.of(22, 0))
                    ));
                    break;
                case "night":
                    predicates.add(cb.or(
                        cb.and(
                            cb.greaterThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(22, 0)),
                            cb.lessThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(23, 59))
                        ),
                        cb.and(
                            cb.greaterThanOrEqualTo(timeTable.get("startTime"), LocalTime.of(0, 0)),
                            cb.lessThan(timeTable.get("startTime"), LocalTime.of(6, 0))
                        )
                    ));
                    break;
            }
        }
        
        return cb.and(predicates.toArray(Predicate[]::new));
    }
} 