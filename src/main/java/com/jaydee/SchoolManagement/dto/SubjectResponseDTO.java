package com.jaydee.SchoolManagement.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SubjectResponseDTO {

	private Long subjectId;

	private String subjectName;

	private String description;

	private String code;

	@JsonFormat(pattern = "MMM dd, yyyy 'at' hh:mm a", timezone = "Asia/Phnom_Penh")
	private LocalDateTime createAt;
}
