package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.ExamResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamResultSpec implements Specification<ExamResult> {
    
    private static final long serialVersionUID = 1L;
    private final ExamResultFilter filter;
    
    public ExamResultSpec(ExamResultFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<ExamResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filter.getStudentId() != null) {
            predicates.add(cb.equal(root.get("student").get("studentId"), filter.getStudentId()));
        }
        
        if (filter.getExamId() != null) {
            predicates.add(cb.equal(root.get("exam").get("examId"), filter.getExamId()));
        }
        
        if (filter.getSubjectId() != null) {
            predicates.add(cb.equal(root.get("exam").get("subject").get("subjectId"), filter.getSubjectId()));
        }
        
        if (filter.getGrade() != null && !filter.getGrade().isEmpty()) {
            predicates.add(cb.equal(root.get("grade"), filter.getGrade()));
        }
        
        if (filter.getMinPercentage() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("percentage"), filter.getMinPercentage()));
        }
        
        if (filter.getMaxPercentage() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("percentage"), filter.getMaxPercentage()));
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 