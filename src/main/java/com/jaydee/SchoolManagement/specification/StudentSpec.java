package com.jaydee.SchoolManagement.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.jaydee.SchoolManagement.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class StudentSpec implements Specification<Student>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final StudentFilter studentFilter;
	
	@Override
	public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		
		// First name filter (case-insensitive)
		if (studentFilter.getFirstName() != null && !studentFilter.getFirstName().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("firstName")), 
				"%" + studentFilter.getFirstName().toLowerCase() + "%"));
		}
		
		// Last name filter (case-insensitive)
		if (studentFilter.getLastName() != null && !studentFilter.getLastName().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("lastName")), 
				"%" + studentFilter.getLastName().toLowerCase() + "%"));
		}
		
		// Full name filter (case-insensitive search in both first and last name)
		if (studentFilter.getFullName() != null && !studentFilter.getFullName().trim().isEmpty()) {
			String searchTerm = "%" + studentFilter.getFullName().toLowerCase() + "%";
			predicates.add(cb.or(
				cb.like(cb.lower(student.get("firstName")), searchTerm),
				cb.like(cb.lower(student.get("lastName")), searchTerm),
				cb.like(cb.lower(cb.concat(student.get("firstName"), " ")), searchTerm),
				cb.like(cb.lower(cb.concat(" ", student.get("lastName"))), searchTerm)
			));
		}
		
		// Gender filter
		if (studentFilter.getGender() != null) {
			predicates.add(cb.equal(student.get("gender"), studentFilter.getGender()));
		}
		
		// Phone number filter
		if (studentFilter.getPhoneNumber() != null && !studentFilter.getPhoneNumber().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("phone_number")), 
				"%" + studentFilter.getPhoneNumber().toLowerCase() + "%"));
		}
		
		// Date of birth range filters
		if (studentFilter.getDateOfBirthFrom() != null) {
			predicates.add(cb.greaterThanOrEqualTo(student.get("date_of_birth"), studentFilter.getDateOfBirthFrom()));
		}
		if (studentFilter.getDateOfBirthTo() != null) {
			predicates.add(cb.lessThanOrEqualTo(student.get("date_of_birth"), studentFilter.getDateOfBirthTo()));
		}
		
		// Place of birth filter
		if (studentFilter.getPlaceOfBirth() != null && !studentFilter.getPlaceOfBirth().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("place_of_birth")), 
				"%" + studentFilter.getPlaceOfBirth().toLowerCase() + "%"));
		}
		
		// Current place filter
		if (studentFilter.getCurrentPlace() != null && !studentFilter.getCurrentPlace().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("current_place")), 
				"%" + studentFilter.getCurrentPlace().toLowerCase() + "%"));
		}
		
		// Emergency phone filter
		if (studentFilter.getEmergencyPhone() != null && !studentFilter.getEmergencyPhone().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(student.get("emergencyPhone")), 
				"%" + studentFilter.getEmergencyPhone().toLowerCase() + "%"));
		}
		
		// Class ID filter (students in specific class)
		if (studentFilter.getClassId() != null) {
			Join<Object, Object> classJoin = student.join("classes");
			predicates.add(cb.equal(classJoin.get("classId"), studentFilter.getClassId()));
		}
		
		// Class name filter (students in classes with specific name)
		if (studentFilter.getClassName() != null && !studentFilter.getClassName().trim().isEmpty()) {
			Join<Object, Object> classJoin = student.join("classes");
			predicates.add(cb.like(cb.lower(classJoin.get("className")), 
				"%" + studentFilter.getClassName().toLowerCase() + "%"));
		}
		
		// Teacher ID filter (students taught by specific teacher)
		if (studentFilter.getTeacherId() != null) {
			Join<Object, Object> classJoin = student.join("classes");
			Join<Object, Object> teacherJoin = classJoin.join("teacher");
			predicates.add(cb.equal(teacherJoin.get("teacherId"), studentFilter.getTeacherId()));
		}
		
		// Creation date range filters
		if (studentFilter.getCreatedFrom() != null) {
			predicates.add(cb.greaterThanOrEqualTo(student.get("createAt"), 
				studentFilter.getCreatedFrom().atStartOfDay()));
		}
		if (studentFilter.getCreatedTo() != null) {
			predicates.add(cb.lessThanOrEqualTo(student.get("createAt"), 
				studentFilter.getCreatedTo().atTime(23, 59, 59)));
		}
		
		// Age range filters (calculated from date of birth)
		if (studentFilter.getAgeFrom() != null) {
			// Calculate maximum birth date for minimum age
			java.time.LocalDate maxBirthDate = java.time.LocalDate.now().minusYears(studentFilter.getAgeFrom());
			predicates.add(cb.lessThanOrEqualTo(student.get("date_of_birth"), maxBirthDate));
		}
		if (studentFilter.getAgeTo() != null) {
			// Calculate minimum birth date for maximum age
			java.time.LocalDate minBirthDate = java.time.LocalDate.now().minusYears(studentFilter.getAgeTo() + 1);
			predicates.add(cb.greaterThan(student.get("date_of_birth"), minBirthDate));
		}
		
		return cb.and(predicates.toArray(Predicate[]::new));
	}
}
