package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.ParentRequestDTO;
import com.jaydee.SchoolManagement.dto.ParentResponseDTO;
import com.jaydee.SchoolManagement.entity.Parent;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.ParentMapper;
import com.jaydee.SchoolManagement.repository.ParentRepository;
import com.jaydee.SchoolManagement.service.ParentService;
import com.jaydee.SchoolManagement.specification.ParentFilter;
import com.jaydee.SchoolManagement.specification.ParentSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ParentServiceImpl implements ParentService {
    
    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    @Override
    public ParentResponseDTO createParent(ParentRequestDTO requestDTO) {
        // Check if email already exists
        if (requestDTO.getEmail() != null && parentRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Parent with this email already exists");
        }
        
        // Check if phone number already exists
        if (requestDTO.getPhoneNumber() != null && parentRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("Parent with this phone number already exists");
        }
        
        Parent parent = parentMapper.toEntity(requestDTO);
        Parent saved = parentRepository.save(parent);
        return parentMapper.toResponseDTO(saved);
    }

    @Override
    public ParentResponseDTO updateParent(Long id, ParentRequestDTO requestDTO) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Parent not found"));
        
        // Check if new email conflicts with existing parent
        if (requestDTO.getEmail() != null && 
            !requestDTO.getEmail().equals(parent.getEmail()) && 
            parentRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Parent with this email already exists");
        }
        
        // Check if new phone number conflicts with existing parent
        if (requestDTO.getPhoneNumber() != null && 
            !requestDTO.getPhoneNumber().equals(parent.getPhoneNumber()) && 
            parentRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("Parent with this phone number already exists");
        }
        
        parent.setFirstName(requestDTO.getFirstName());
        parent.setLastName(requestDTO.getLastName());
        parent.setGender(requestDTO.getGender());
        parent.setPhoneNumber(requestDTO.getPhoneNumber());
        parent.setEmail(requestDTO.getEmail());
        parent.setAddress(requestDTO.getAddress());
        parent.setOccupation(requestDTO.getOccupation());
        parent.setDateOfBirth(requestDTO.getDateOfBirth());
        
        Parent updated = parentRepository.save(parent);
        return parentMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteParent(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Parent not found"));
        parentRepository.delete(parent);
    }

    @Override
    public ParentResponseDTO getParentById(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Parent not found"));
        return parentMapper.toResponseDTO(parent);
    }

    @Override
    public List<ParentResponseDTO> getAllParents() {
        return parentRepository.findAll()
                .stream()
                .map(parentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParentResponseDTO> findParentsByFilter(ParentFilter filter) {
        ParentSpec spec = new ParentSpec(filter);
        return parentRepository.findAll(spec)
                .stream()
                .map(parentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParentResponseDTO> findParentsByName(String name) {
        return parentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(parentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParentResponseDTO> findParentsByEmail(String email) {
        return parentRepository.findByEmail(email)
                .stream()
                .map(parentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParentResponseDTO> findParentsByOccupation(String occupation) {
        return parentRepository.findByOccupationContainingIgnoreCase(occupation)
                .stream()
                .map(parentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
} 