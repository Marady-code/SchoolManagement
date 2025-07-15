package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;

import com.jaydee.SchoolManagement.entity.Attendance;

import jakarta.persistence.criteria.Predicate;

public class AttendanceSpec implements Specification<Attendance> {
    private final AttendanceFilter filter;

    public AttendanceSpec(AttendanceFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(jakarta.persistence.criteria.Root<Attendance> root,
                                 jakarta.persistence.criteria.CriteriaQuery<?> query,
                                 jakarta.persistence.criteria.CriteriaBuilder cb) {
        java.util.List<Predicate> predicates = new java.util.ArrayList<>();
        if (filter.getStudentId() != null) {
            predicates.add(cb.equal(root.get("student").get("studentId"), filter.getStudentId()));
        }
        if (filter.getClassId() != null) {
            predicates.add(cb.equal(root.get("classEntity").get("classId"), filter.getClassId()));
        }
        if (filter.getTeacherId() != null) {
            predicates.add(cb.equal(root.get("recordBy").get("teacherId"), filter.getTeacherId()));
        }
        if (filter.getDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("attendanceDate"), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("attendanceDate"), filter.getDateTo()));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), filter.getStatus()));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 