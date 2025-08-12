package com.jaydee.SchoolManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaydee.SchoolManagement.dto.AdminRequestDTO;
import com.jaydee.SchoolManagement.dto.AdminResponseDTO;
import com.jaydee.SchoolManagement.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;
    
    @PostMapping
    public ResponseEntity<AdminResponseDTO> createAdmin(@RequestBody AdminRequestDTO requestDTO) {
        AdminResponseDTO response = adminService.createAdmin(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminRequestDTO requestDTO) {
        AdminResponseDTO response = adminService.updateAdmin(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id) {
        AdminResponseDTO response = adminService.getAdminById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {
        List<AdminResponseDTO> response = adminService.getAllAdmins();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<AdminResponseDTO>> findAdminsByName(@RequestParam String name) {
        List<AdminResponseDTO> response = adminService.findAdminsByName(name);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/role")
    public ResponseEntity<List<AdminResponseDTO>> findAdminsByRole(@RequestParam String role) {
        List<AdminResponseDTO> response = adminService.findAdminsByRole(role);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<AdminResponseDTO>> findActiveAdmins() {
        List<AdminResponseDTO> response = adminService.findActiveAdmins();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/inactive")
    public ResponseEntity<List<AdminResponseDTO>> findInactiveAdmins() {
        List<AdminResponseDTO> response = adminService.findInactiveAdmins();
        return ResponseEntity.ok(response);
    }
} 