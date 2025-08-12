package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.AdminRequestDTO;
import com.jaydee.SchoolManagement.dto.AdminResponseDTO;
import com.jaydee.SchoolManagement.specification.AdminFilter;

public interface AdminService {
    
    AdminResponseDTO createAdmin(AdminRequestDTO requestDTO);
    
    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO requestDTO);
    
    void deleteAdmin(Long id);
    
    AdminResponseDTO getAdminById(Long id);
    
    List<AdminResponseDTO> getAllAdmins();
    
    List<AdminResponseDTO> findAdminsByFilter(AdminFilter filter);
    
    List<AdminResponseDTO> findAdminsByName(String name);
    
    List<AdminResponseDTO> findAdminsByRole(String role);
    
    List<AdminResponseDTO> findActiveAdmins();
    
    List<AdminResponseDTO> findInactiveAdmins();
} 