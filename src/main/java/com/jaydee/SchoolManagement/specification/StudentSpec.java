package com.jaydee.SchoolManagement.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jaydee.SchoolManagement.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class StudentSpec implements Specification<Student>{

	private final StudentFilter studentFilter;
	
	List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		if(studentFilter.getFirstName() != null) {
			Predicate namePredicate = cb.or(
					cb.like(cb.upper(student.get("firstName")), "%" + studentFilter.getFirstName().toUpperCase() + "%")
//					cb.like(cb.lower(root.get("firstName")), "%", studentFilter.getFirstName().toLowerCase() + "%"),
//					cb.like(cb.lower(root.get("lastName")), "%", studentFilter.getLastName().toLowerCase() + "%")
			);
			
			predicates.add(namePredicate);
		}
		
		return cb.and(predicates.toArray(Predicate[]::new));
	}

}
