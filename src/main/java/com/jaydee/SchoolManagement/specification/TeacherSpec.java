package com.jaydee.SchoolManagement.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Teacher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class TeacherSpec implements Specification<Teacher> {

    private final TeacherFilter teacherFilter;
    
    public TeacherSpec(TeacherFilter teacherFilter) {
        this.teacherFilter = teacherFilter;
    }
    
    @Override
    public Predicate toPredicate(Root<Teacher> teacher, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        // First name filter (case-insensitive)
        if (teacherFilter.getFirstName() != null && !teacherFilter.getFirstName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("firstName")), 
                "%" + teacherFilter.getFirstName().toLowerCase() + "%"));
        }
        
        // Last name filter (case-insensitive)
        if (teacherFilter.getLastName() != null && !teacherFilter.getLastName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("lastName")), 
                "%" + teacherFilter.getLastName().toLowerCase() + "%"));
        }
        
        // Full name filter (case-insensitive search in both first and last name)
        if (teacherFilter.getFullName() != null && !teacherFilter.getFullName().trim().isEmpty()) {
            String searchTerm = "%" + teacherFilter.getFullName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(teacher.get("firstName")), searchTerm),
                cb.like(cb.lower(teacher.get("lastName")), searchTerm),
                cb.like(cb.lower(cb.concat(teacher.get("firstName"), " ")), searchTerm),
                cb.like(cb.lower(cb.concat(" ", teacher.get("lastName"))), searchTerm)
            ));
        }
        
        // Gender filter
        if (teacherFilter.getGender() != null) {
            predicates.add(cb.equal(teacher.get("gender"), teacherFilter.getGender()));
        }
        
        // Phone number filter
        if (teacherFilter.getPhoneNumber() != null && !teacherFilter.getPhoneNumber().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("phone_number")), 
                "%" + teacherFilter.getPhoneNumber().toLowerCase() + "%"));
        }
        
        // Date of birth range filters
        if (teacherFilter.getDateOfBirthFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(teacher.get("date_of_birth"), teacherFilter.getDateOfBirthFrom()));
        }
        if (teacherFilter.getDateOfBirthTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(teacher.get("date_of_birth"), teacherFilter.getDateOfBirthTo()));
        }
        
        // Place of birth filter
        if (teacherFilter.getPlaceOfBirth() != null && !teacherFilter.getPlaceOfBirth().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("place_of_birth")), 
                "%" + teacherFilter.getPlaceOfBirth().toLowerCase() + "%"));
        }
        
        // Current place filter
        if (teacherFilter.getCurrentPlace() != null && !teacherFilter.getCurrentPlace().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("current_place")), 
                "%" + teacherFilter.getCurrentPlace().toLowerCase() + "%"));
        }
        
        // Qualification filter
        if (teacherFilter.getQualification() != null && !teacherFilter.getQualification().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(teacher.get("qualification")), 
                "%" + teacherFilter.getQualification().toLowerCase() + "%"));
        }
        
        // Joining date range filters
        if (teacherFilter.getJoiningDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(teacher.get("joining_date"), teacherFilter.getJoiningDateFrom()));
        }
        if (teacherFilter.getJoiningDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(teacher.get("joining_date"), teacherFilter.getJoiningDateTo()));
        }
        
        // Salary range filters
        if (teacherFilter.getSalaryFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(teacher.get("salary"), teacherFilter.getSalaryFrom()));
        }
        if (teacherFilter.getSalaryTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(teacher.get("salary"), teacherFilter.getSalaryTo()));
        }
        
        // Class ID filter (teachers teaching specific class)
        if (teacherFilter.getClassId() != null) {
            Join<Object, Object> classJoin = teacher.join("classes");
            predicates.add(cb.equal(classJoin.get("classId"), teacherFilter.getClassId()));
        }
        
        // Class name filter (teachers teaching classes with specific name)
        if (teacherFilter.getClassName() != null && !teacherFilter.getClassName().trim().isEmpty()) {
            Join<Object, Object> classJoin = teacher.join("classes");
            predicates.add(cb.like(cb.lower(classJoin.get("className")), 
                "%" + teacherFilter.getClassName().toLowerCase() + "%"));
        }
        
        // Student ID filter (teachers teaching specific student)
        if (teacherFilter.getStudentId() != null) {
            Join<Object, Object> classJoin = teacher.join("classes");
            Join<Object, Object> studentJoin = classJoin.join("students");
            predicates.add(cb.equal(studentJoin.get("studentId"), teacherFilter.getStudentId()));
        }
        
        // Student name filter (teachers teaching student with specific name)
        if (teacherFilter.getStudentName() != null && !teacherFilter.getStudentName().trim().isEmpty()) {
            Join<Object, Object> classJoin = teacher.join("classes");
            Join<Object, Object> studentJoin = classJoin.join("students");
            String searchTerm = "%" + teacherFilter.getStudentName().toLowerCase() + "%";
            predicates.add(cb.or(
                cb.like(cb.lower(studentJoin.get("firstName")), searchTerm),
                cb.like(cb.lower(studentJoin.get("lastName")), searchTerm)
            ));
        }
        
        // Creation date range filters
        if (teacherFilter.getCreatedFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(teacher.get("createAt"), 
                teacherFilter.getCreatedFrom().atStartOfDay()));
        }
        if (teacherFilter.getCreatedTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(teacher.get("createAt"), 
                teacherFilter.getCreatedTo().atTime(23, 59, 59)));
        }
        
        // Age range filters (calculated from date of birth)
        if (teacherFilter.getAgeFrom() != null) {
            // Calculate maximum birth date for minimum age
            java.time.LocalDate maxBirthDate = java.time.LocalDate.now().minusYears(teacherFilter.getAgeFrom());
            predicates.add(cb.lessThanOrEqualTo(teacher.get("date_of_birth"), maxBirthDate));
        }
        if (teacherFilter.getAgeTo() != null) {
            // Calculate minimum birth date for maximum age
            java.time.LocalDate minBirthDate = java.time.LocalDate.now().minusYears(teacherFilter.getAgeTo() + 1);
            predicates.add(cb.greaterThan(teacher.get("date_of_birth"), minBirthDate));
        }
        
        // Active/Inactive filter (based on joining date)
        if (teacherFilter.getIsActive() != null) {
            if (teacherFilter.getIsActive()) {
                // Active teachers: joining date is today or in the past
                predicates.add(cb.lessThanOrEqualTo(teacher.get("joining_date"), java.time.LocalDate.now()));
            } else {
                // Inactive teachers: joining date is in the future
                predicates.add(cb.greaterThan(teacher.get("joining_date"), java.time.LocalDate.now()));
            }
        }
        
        // Experience years range filters (calculated from joining date)
        if (teacherFilter.getExperienceYearsFrom() != null) {
            // Calculate maximum joining date for minimum experience
            java.time.LocalDate maxJoiningDate = java.time.LocalDate.now().minusYears(teacherFilter.getExperienceYearsFrom());
            predicates.add(cb.lessThanOrEqualTo(teacher.get("joining_date"), maxJoiningDate));
        }
        if (teacherFilter.getExperienceYearsTo() != null) {
            // Calculate minimum joining date for maximum experience
            java.time.LocalDate minJoiningDate = java.time.LocalDate.now().minusYears(teacherFilter.getExperienceYearsTo() + 1);
            predicates.add(cb.greaterThan(teacher.get("joining_date"), minJoiningDate));
        }
        
        return cb.and(predicates.toArray(Predicate[]::new));
    }
} 