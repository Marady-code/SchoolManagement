package com.jaydee.SchoolManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jaydee.SchoolManagement.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {
    
    Optional<Admin> findByEmail(String email);
    
    Optional<Admin> findByUsername(String username);
    
    List<Admin> findByRole(String role);
    
    List<Admin> findByIsActive(Boolean isActive);
    
    List<Admin> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    
    boolean existsByEmail(String email);
    
    boolean existsByUsername(String username);
} 