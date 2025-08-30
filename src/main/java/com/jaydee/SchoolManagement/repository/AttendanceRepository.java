package com.jaydee.SchoolManagement.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    List<Attendance> findByStudentStudentId(Long studentId);
    List<Attendance> findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Attendance> findByClassEntityClassNameContainingIgnoreCase(String className);
    @Query("SELECT a FROM Attendance a WHERE a.classEntity.className = :className AND a.date = :date")
    List<Attendance> findByClassEntityClassNameAndDate(@Param("className") String className, @Param("date") LocalDate date);
    boolean existsByStudentStudentIdAndClassEntityClassIdAndDate(Long studentId, Long classId, LocalDateTime date);

    @Query("SELECT a FROM Attendance a WHERE " +
            "LOWER(CONCAT(a.student.firstName, ' ', a.student.lastName)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<Attendance> findByStudentFullName(@Param("fullName") String fullName);

} 