package com.jaydee.SchoolManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long>, JpaSpecificationExecutor<Parent> {
    
    Optional<Parent> findByEmail(String email);
    
    Optional<Parent> findByPhoneNumber(String phoneNumber);
    
    List<Parent> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    
    List<Parent> findByOccupationContainingIgnoreCase(String occupation);
    
    boolean existsByEmail(String email);
    
    boolean existsByPhoneNumber(String phoneNumber);
} 