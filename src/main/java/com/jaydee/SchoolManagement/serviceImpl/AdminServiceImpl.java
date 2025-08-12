package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.AdminRequestDTO;
import com.jaydee.SchoolManagement.dto.AdminResponseDTO;
import com.jaydee.SchoolManagement.entity.Admin;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.AdminMapper;
import com.jaydee.SchoolManagement.repository.AdminRepository;
import com.jaydee.SchoolManagement.service.AdminService;
import com.jaydee.SchoolManagement.specification.AdminFilter;
import com.jaydee.SchoolManagement.specification.AdminSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO requestDTO) {
        // Check if email already exists
        if (adminRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Admin with this email already exists");
        }
        
        // Check if username already exists
        if (adminRepository.existsByUsername(requestDTO.getUsername())) {
            throw new IllegalArgumentException("Admin with this username already exists");
        }
        
        Admin admin = adminMapper.toEntity(requestDTO);
        Admin saved = adminRepository.save(admin);
        return adminMapper.toResponseDTO(saved);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO requestDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Admin not found"));
        
        // Check if new email conflicts with existing admin
        if (!requestDTO.getEmail().equals(admin.getEmail()) && 
            adminRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Admin with this email already exists");
        }
        
        // Check if new username conflicts with existing admin
        if (!requestDTO.getUsername().equals(admin.getUsername()) && 
            adminRepository.existsByUsername(requestDTO.getUsername())) {
            throw new IllegalArgumentException("Admin with this username already exists");
        }
        
        admin.setFirstName(requestDTO.getFirstName());
        admin.setLastName(requestDTO.getLastName());
        admin.setGender(requestDTO.getGender());
        admin.setEmail(requestDTO.getEmail());
        admin.setPhoneNumber(requestDTO.getPhoneNumber());
        admin.setAddress(requestDTO.getAddress());
        admin.setUsername(requestDTO.getUsername());
        admin.setPassword(requestDTO.getPassword());
        admin.setRole(requestDTO.getRole());
        
        Admin updated = adminRepository.save(admin);
        return adminMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Admin not found"));
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Admin not found"));
        return adminMapper.toResponseDTO(admin);
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDTO> findAdminsByFilter(AdminFilter filter) {
        AdminSpec spec = new AdminSpec(filter);
        return adminRepository.findAll(spec)
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDTO> findAdminsByName(String name) {
        return adminRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDTO> findAdminsByRole(String role) {
        return adminRepository.findByRole(role)
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDTO> findActiveAdmins() {
        return adminRepository.findByIsActive(true)
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminResponseDTO> findInactiveAdmins() {
        return adminRepository.findByIsActive(false)
                .stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
} 