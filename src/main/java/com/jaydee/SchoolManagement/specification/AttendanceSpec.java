package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Attendance;
import com.jaydee.SchoolManagement.entity.AttendanceStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttendanceSpec implements Specification<Attendance> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final AttendanceFilter filter;

    @Override
    public Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        // Student ID filter
        if (filter.getStudentId() != null) {
            predicates.add(cb.equal(root.get("student").get("studentId"), filter.getStudentId()));
        }
        
        // Student name search (case-insensitive)
        if (filter.getStudentName() != null && !filter.getStudentName().trim().isEmpty()) {
            String searchTerm = "%" + filter.getStudentName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(root.get("student").get("firstName")), searchTerm),
                cb.like(cb.lower(root.get("student").get("lastName")), searchTerm),
                cb.like(cb.lower(cb.concat(root.get("student").get("firstName"), " ")), searchTerm),
                cb.like(cb.lower(cb.concat(" ", root.get("student").get("lastName"))), searchTerm)
            ));
        }
        
        // Student first name filter
        if (filter.getStudentFirstName() != null && !filter.getStudentFirstName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("student").get("firstName")), 
                "%" + filter.getStudentFirstName().toLowerCase() + "%"));
        }
        
        // Student last name filter
        if (filter.getStudentLastName() != null && !filter.getStudentLastName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("student").get("lastName")), 
                "%" + filter.getStudentLastName().toLowerCase() + "%"));
        }
        
        // Class ID filter
        if (filter.getClassId() != null) {
            predicates.add(cb.equal(root.get("classEntity").get("classId"), filter.getClassId()));
        }
        
        // Class name filter
        if (filter.getClassName() != null && !filter.getClassName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("classEntity").get("className")), 
                "%" + filter.getClassName().toLowerCase() + "%"));
        }
        
        // Teacher ID filter
        if (filter.getTeacherId() != null) {
            predicates.add(cb.equal(root.get("recordBy").get("teacherId"), filter.getTeacherId()));
        }
        
        // Teacher name filter
        if (filter.getTeacherName() != null && !filter.getTeacherName().trim().isEmpty()) {
            String searchTerm = "%" + filter.getTeacherName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(root.get("recordBy").get("firstName")), searchTerm),
                cb.like(cb.lower(root.get("recordBy").get("lastName")), searchTerm)
            ));
        }
        
        // Date range filters
        if (filter.getDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"), filter.getDateTo()));
        }
        
        // Specific date filter
        if (filter.getSpecificDate() != null) {
            predicates.add(cb.equal(root.get("date"), filter.getSpecificDate()));
        }
        
        // Status filter
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), filter.getStatus()));
        }
        
        // Present/Absent filter
        if (filter.getIsPresent() != null) {
            if (filter.getIsPresent()) {
                predicates.add(cb.equal(root.get("status"), AttendanceStatus.PRESENT));
            } else {
                predicates.add(cb.or(
                    cb.equal(root.get("status"), AttendanceStatus.ABSENT),
                    cb.equal(root.get("status"), AttendanceStatus.LATE)
                ));
            }
        }
        
        // Remark filter
        if (filter.getRemark() != null && !filter.getRemark().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("remark")), 
                "%" + filter.getRemark().toLowerCase() + "%"));
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}

