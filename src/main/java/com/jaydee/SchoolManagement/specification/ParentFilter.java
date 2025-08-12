package com.jaydee.SchoolManagement.specification;

import com.jaydee.SchoolManagement.entity.GenderEnum;

import lombok.Data;

@Data
public class ParentFilter {
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phoneNumber;
    
    private String occupation;
    
    private GenderEnum gender;
} 