package com.jaydee.SchoolManagement.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, JpaSpecificationExecutor<TimeTable> {
    
    List<TimeTable> findByDayOfWeek(DayOfWeek dayOfWeek);
    
    List<TimeTable> findByTeacherTeacherId(Long teacherId);
    
    List<TimeTable> findBySubjectSubjectId(Long subjectId);
    
    List<TimeTable> findByClassEntityClassId(Long classId);
    
    List<TimeTable> findByDayOfWeekAndClassEntityClassId(DayOfWeek dayOfWeek, Long classId);
    
    List<TimeTable> findByDayOfWeekAndTeacherTeacherId(DayOfWeek dayOfWeek, Long teacherId);
    
    List<TimeTable> findByStartTimeBetween(LocalTime startTime, LocalTime endTime);
} 