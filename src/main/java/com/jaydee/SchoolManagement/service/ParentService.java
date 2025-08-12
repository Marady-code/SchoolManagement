package com.jaydee.SchoolManagement.service;

import java.util.List;

import com.jaydee.SchoolManagement.dto.ParentRequestDTO;
import com.jaydee.SchoolManagement.dto.ParentResponseDTO;
import com.jaydee.SchoolManagement.specification.ParentFilter;

public interface ParentService {
    
    ParentResponseDTO createParent(ParentRequestDTO requestDTO);
    
    ParentResponseDTO updateParent(Long id, ParentRequestDTO requestDTO);
    
    void deleteParent(Long id);
    
    ParentResponseDTO getParentById(Long id);
    
    List<ParentResponseDTO> getAllParents();
    
    List<ParentResponseDTO> findParentsByFilter(ParentFilter filter);
    
    List<ParentResponseDTO> findParentsByName(String name);
    
    List<ParentResponseDTO> findParentsByEmail(String email);
    
    List<ParentResponseDTO> findParentsByOccupation(String occupation);
} 