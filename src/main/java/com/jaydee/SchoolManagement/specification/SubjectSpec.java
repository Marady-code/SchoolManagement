package com.jaydee.SchoolManagement.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jaydee.SchoolManagement.entity.Subject;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class SubjectSpec implements Specification<Subject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SubjectFilter filter;

	@Override
	public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getSubjectName() != null && !filter.getSubjectName().isEmpty()) {
			predicates
					.add(cb.like(cb.lower(root.get("subjectName")), "%" + filter.getSubjectName().toLowerCase() + "%"));
		}
		
		if(filter.getCode() != null && !filter.getCode().isEmpty()) {
			predicates
					.add(cb.like(cb.lower(root.get("code")), "%" + filter.getCode().toLowerCase() + "%"));
		}

		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
