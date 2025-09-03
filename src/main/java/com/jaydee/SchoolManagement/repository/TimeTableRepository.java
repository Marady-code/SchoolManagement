package com.jaydee.SchoolManagement.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, JpaSpecificationExecutor<TimeTable> {

	List<TimeTable> findByClassEntity_ClassId(Long classId);
    List<TimeTable> findByTeacher_TeacherId(Long teacherId);
    
    // Fixed method names and parameter order
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
           "FROM TimeTable t WHERE t.classEntity.classId = :classId " +
           "AND t.dayOfWeek = :dayOfWeek " +
           "AND ((t.startTime <= :endTime AND t.endTime >= :startTime))")
    boolean existsClassConflict(@Param("classId") Long classId, 
                               @Param("dayOfWeek") DayOfWeek dayOfWeek,
                               @Param("startTime") LocalTime startTime,
                               @Param("endTime") LocalTime endTime);
    
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
           "FROM TimeTable t WHERE t.teacher.teacherId = :teacherId " +
           "AND t.dayOfWeek = :dayOfWeek " +
           "AND ((t.startTime <= :endTime AND t.endTime >= :startTime))")
    boolean existsTeacherConflict(@Param("teacherId") Long teacherId, 
                                 @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                 @Param("startTime") LocalTime startTime,
                                 @Param("endTime") LocalTime endTime);
}