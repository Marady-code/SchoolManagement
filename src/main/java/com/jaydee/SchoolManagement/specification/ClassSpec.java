package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;

import com.jaydee.SchoolManagement.entity.ClassEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class ClassSpec implements Specification<ClassEntity> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9080615340282103356L;
	private final ClassFilter classFilter;

    @Override
    public Predicate toPredicate(Root<ClassEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (classFilter.getClassName() != null) {
            predicate = cb.and(predicate, cb.like(cb.upper(root.get("className")), "%" + classFilter.getClassName().toUpperCase() + "%"));
        }
        if (classFilter.getTeacherId() != null) {
            predicate = cb.and(predicate, cb.equal(root.get("teacher").get("teacherId"), classFilter.getTeacherId()));
        }
        if (classFilter.getRoomNumber() != null) {
            predicate = cb.and(predicate, cb.like(cb.upper(root.get("roomNumber")), "%" + classFilter.getRoomNumber().toUpperCase() + "%"));
        }
        return predicate;
    }
} 