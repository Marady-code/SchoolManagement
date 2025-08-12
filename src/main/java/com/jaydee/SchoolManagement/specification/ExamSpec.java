package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Exam;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamSpec implements Specification<Exam> {
    
    private static final long serialVersionUID = 1L;
    private final ExamFilter filter;
    
    public ExamSpec(ExamFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Exam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filter.getExamTitle() != null && !filter.getExamTitle().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("examTitle")),
                "%" + filter.getExamTitle().toLowerCase() + "%"
            ));
        }
        
        if (filter.getExamType() != null && !filter.getExamType().isEmpty()) {
            predicates.add(cb.equal(root.get("examType"), filter.getExamType()));
        }
        
        if (filter.getSubjectId() != null) {
            predicates.add(cb.equal(root.get("subject").get("subjectId"), filter.getSubjectId()));
        }
        
        if (filter.getClassId() != null) {
            predicates.add(cb.equal(root.get("classEntity").get("classId"), filter.getClassId()));
        }
        
        if (filter.getExamDate() != null) {
            predicates.add(cb.equal(root.get("examDate"), filter.getExamDate()));
        }
        
        if (filter.getDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("examDate"), filter.getDateFrom()));
        }
        
        if (filter.getDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("examDate"), filter.getDateTo()));
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 