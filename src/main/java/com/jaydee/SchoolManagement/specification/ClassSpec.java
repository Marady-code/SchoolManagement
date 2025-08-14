package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClassSpec implements Specification<ClassEntity> {
    private static final long serialVersionUID = -9080615340282103356L;
    private final ClassFilter classFilter;

    public ClassSpec(ClassFilter classFilter) {
        this.classFilter = classFilter;
    }

    @Override
    public Predicate toPredicate(Root<ClassEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        // Class name filter (case-insensitive)
        if (classFilter.getClassName() != null && !classFilter.getClassName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("className")), 
                "%" + classFilter.getClassName().toLowerCase() + "%"));
        }
        
        // Teacher ID filter
        if (classFilter.getTeacherId() != null) {
            predicates.add(cb.equal(root.get("teacher").get("teacherId"), classFilter.getTeacherId()));
        }
        
        // Teacher name filter (case-insensitive)
        if (classFilter.getTeacherName() != null && !classFilter.getTeacherName().trim().isEmpty()) {
            String searchTerm = "%" + classFilter.getTeacherName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(root.get("teacher").get("firstName")), searchTerm),
                cb.like(cb.lower(root.get("teacher").get("lastName")), searchTerm)
            ));
        }
        
        // Room number filter (case-insensitive)
        if (classFilter.getRoomNumber() != null && !classFilter.getRoomNumber().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("roomNumber")), 
                "%" + classFilter.getRoomNumber().toLowerCase() + "%"));
        }
        
        // Class day filter
        if (classFilter.getClassDay() != null && !classFilter.getClassDay().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("classDay")), 
                "%" + classFilter.getClassDay().toLowerCase() + "%"));
        }
        
        // Class time filter
        if (classFilter.getClassTime() != null && !classFilter.getClassTime().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("classTime")), 
                "%" + classFilter.getClassTime().toLowerCase() + "%"));
        }
        
        // Start date range filters
        if (classFilter.getStartDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), classFilter.getStartDateFrom()));
        }
        if (classFilter.getStartDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), classFilter.getStartDateTo()));
        }
        
        // Student ID filter (classes containing specific student)
        if (classFilter.getStudentId() != null) {
            Join<Object, Object> studentJoin = root.join("students");
            predicates.add(cb.equal(studentJoin.get("studentId"), classFilter.getStudentId()));
        }
        
        // Student name filter (classes containing student with specific name)
        if (classFilter.getStudentName() != null && !classFilter.getStudentName().trim().isEmpty()) {
            Join<Object, Object> studentJoin = root.join("students");
            String searchTerm = "%" + classFilter.getStudentName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(studentJoin.get("firstName")), searchTerm),
                cb.like(cb.lower(studentJoin.get("lastName")), searchTerm)
            ));
        }
        
        // Creation date range filters
        if (classFilter.getCreatedFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createAt"), 
                classFilter.getCreatedFrom().atStartOfDay()));
        }
        if (classFilter.getCreatedTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createAt"), 
                classFilter.getCreatedTo().atTime(23, 59, 59)));
        }
        
        // Active/Inactive filter (based on start date)
        if (classFilter.getIsActive() != null) {
            if (classFilter.getIsActive()) {
                // Active classes: start date is today or in the past
                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), java.time.LocalDate.now()));
            } else {
                // Inactive classes: start date is in the future
                predicates.add(cb.greaterThan(root.get("startDate"), java.time.LocalDate.now()));
            }
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 