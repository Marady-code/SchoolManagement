package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Parent;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParentSpec implements Specification<Parent> {
    
    private static final long serialVersionUID = 1L;
    private final ParentFilter filter;
    
    public ParentSpec(ParentFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Parent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("firstName")),
                "%" + filter.getFirstName().toLowerCase() + "%"
            ));
        }
        
        if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("lastName")),
                "%" + filter.getLastName().toLowerCase() + "%"
            ));
        }
        
        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("email")),
                "%" + filter.getEmail().toLowerCase() + "%"
            ));
        }
        
        if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("phoneNumber")),
                "%" + filter.getPhoneNumber().toLowerCase() + "%"
            ));
        }
        
        if (filter.getOccupation() != null && !filter.getOccupation().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("occupation")),
                "%" + filter.getOccupation().toLowerCase() + "%"
            ));
        }
        
        if (filter.getGender() != null) {
            predicates.add(cb.equal(root.get("gender"), filter.getGender()));
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 