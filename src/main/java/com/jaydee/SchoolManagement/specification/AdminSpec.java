package com.jaydee.SchoolManagement.specification;

import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Admin;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdminSpec implements Specification<Admin> {
    
    private static final long serialVersionUID = 1L;
    private final AdminFilter filter;
    
    public AdminSpec(AdminFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        
        if (filter.getUsername() != null && !filter.getUsername().isEmpty()) {
            predicates.add(cb.like(
                cb.lower(root.get("username")),
                "%" + filter.getUsername().toLowerCase() + "%"
            ));
        }
        
        if (filter.getRole() != null && !filter.getRole().isEmpty()) {
            predicates.add(cb.equal(root.get("role"), filter.getRole()));
        }
        
        if (filter.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), filter.getIsActive()));
        }
        
        if (filter.getGender() != null) {
            predicates.add(cb.equal(root.get("gender"), filter.getGender()));
        }
        
        return cb.and(predicates.toArray(new Predicate[0]));
    }
} 