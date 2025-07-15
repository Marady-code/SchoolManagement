package com.jaydee.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassRepository extends JpaRepository<ClassEntity, Long>, JpaSpecificationExecutor<ClassEntity> {
} 