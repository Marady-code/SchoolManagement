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

import com.jaydee.SchoolManagement.dto.ParentRequestDTO;
import com.jaydee.SchoolManagement.dto.ParentResponseDTO;
import com.jaydee.SchoolManagement.service.ParentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentController {
    
    private final ParentService parentService;
    
    @PostMapping
    public ResponseEntity<ParentResponseDTO> createParent(@RequestBody ParentRequestDTO requestDTO) {
        ParentResponseDTO response = parentService.createParent(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ParentResponseDTO> updateParent(@PathVariable Long id, @RequestBody ParentRequestDTO requestDTO) {
        ParentResponseDTO response = parentService.updateParent(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ParentResponseDTO> getParentById(@PathVariable Long id) {
        ParentResponseDTO response = parentService.getParentById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<ParentResponseDTO>> getAllParents() {
        List<ParentResponseDTO> response = parentService.getAllParents();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ParentResponseDTO>> findParentsByName(@RequestParam String name) {
        List<ParentResponseDTO> response = parentService.findParentsByName(name);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search/email")
    public ResponseEntity<List<ParentResponseDTO>> findParentsByEmail(@RequestParam String email) {
        List<ParentResponseDTO> response = parentService.findParentsByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search/occupation")
    public ResponseEntity<List<ParentResponseDTO>> findParentsByOccupation(@RequestParam String occupation) {
        List<ParentResponseDTO> response = parentService.findParentsByOccupation(occupation);
        return ResponseEntity.ok(response);
    }
} 