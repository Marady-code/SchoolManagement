package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class AdminFilter {
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String username;
    
    private String role;
    
    private Boolean isActive;
    
    private GenderEnum gender;
} 