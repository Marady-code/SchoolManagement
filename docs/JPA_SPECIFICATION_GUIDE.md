# JPA Specification Filter Guide

This guide explains how to use the comprehensive JPA Specification filters created for the School Management System.

## Overview

The system includes filter and specification classes for all major entities:
- **Attendance** - Filter attendance records with various criteria
- **Student** - Filter students with comprehensive search options
- **Teacher** - Filter teachers with detailed criteria
- **Class** - Filter classes with multiple search parameters
- **TimeTable** - Filter schedules and timetables

## General Usage Pattern

All filters follow the same pattern:

```java
// 1. Create a filter object
AttendanceFilter filter = new AttendanceFilter();
filter.setStudentName("john");
filter.setDateFrom(LocalDate.now().minusDays(7));

// 2. Create specification
AttendanceSpec spec = new AttendanceSpec(filter);

// 3. Use in repository
List<Attendance> results = attendanceRepository.findAll(spec);
```

## 1. Attendance Filter

### Filter Options:
- `studentId` - Filter by specific student ID
- `studentName` - Case-insensitive search in student names (first or last)
- `studentFirstName` - Search in student first name only
- `studentLastName` - Search in student last name only
- `classId` - Filter by specific class ID
- `className` - Search in class names
- `teacherId` - Filter by specific teacher ID
- `teacherName` - Search in teacher names
- `dateFrom` / `dateTo` - Date range filter
- `specificDate` - Exact date filter
- `status` - Filter by attendance status (PRESENT, ABSENT, LATE)
- `isPresent` - Boolean filter for present/absent
- `remark` - Search in remarks

### Example Usage:
```java
AttendanceFilter filter = new AttendanceFilter();
filter.setStudentName("john"); // Searches in both first and last name
filter.setDateFrom(LocalDate.now().minusDays(30));
filter.setStatus(AttendanceStatus.PRESENT);
filter.setIsPresent(true); // Only present students

AttendanceSpec spec = new AttendanceSpec(filter);
List<Attendance> presentStudents = attendanceRepository.findAll(spec);
```

## 2. Student Filter

### Filter Options:
- `firstName` / `lastName` - Individual name searches
- `fullName` - Search in both first and last names
- `gender` - Filter by gender (MALE, FEMALE)
- `phoneNumber` - Search in phone numbers
- `dateOfBirthFrom` / `dateOfBirthTo` - Date of birth range
- `placeOfBirth` / `currentPlace` - Location searches
- `emergencyPhone` - Search in emergency contact
- `classId` - Students in specific class
- `className` - Students in classes with specific name
- `teacherId` - Students taught by specific teacher
- `createdFrom` / `createdTo` - Creation date range
- `ageFrom` / `ageTo` - Age range (calculated from DOB)

### Example Usage:
```java
StudentFilter filter = new StudentFilter();
filter.setFullName("john"); // Searches in both first and last name
filter.setAgeFrom(15);
filter.setAgeTo(18);
filter.setGender(GenderEnum.MALE);
filter.setClassName("Mathematics");

StudentSpec spec = new StudentSpec(filter);
List<Student> maleStudents = studentRepository.findAll(spec);
```

## 3. Teacher Filter

### Filter Options:
- `firstName` / `lastName` - Individual name searches
- `fullName` - Search in both first and last names
- `gender` - Filter by gender
- `phoneNumber` - Search in phone numbers
- `dateOfBirthFrom` / `dateOfBirthTo` - Date of birth range
- `placeOfBirth` / `currentPlace` - Location searches
- `qualification` - Search in qualifications
- `joiningDateFrom` / `joiningDateTo` - Joining date range
- `salaryFrom` / `salaryTo` - Salary range
- `classId` - Teachers teaching specific class
- `className` - Teachers teaching classes with specific name
- `studentId` - Teachers teaching specific student
- `studentName` - Teachers teaching student with specific name
- `createdFrom` / `createdTo` - Creation date range
- `ageFrom` / `ageTo` - Age range
- `isActive` - Active/inactive teachers
- `experienceYearsFrom` / `experienceYearsTo` - Experience range

### Example Usage:
```java
TeacherFilter filter = new TeacherFilter();
filter.setFullName("smith");
filter.setQualification("PhD");
filter.setSalaryFrom(50000L);
filter.setExperienceYearsFrom(5);
filter.setIsActive(true);

TeacherSpec spec = new TeacherSpec(filter);
List<Teacher> experiencedTeachers = teacherRepository.findAll(spec);
```

## 4. Class Filter

### Filter Options:
- `className` - Search in class names
- `teacherId` - Classes taught by specific teacher
- `teacherName` - Classes taught by teacher with specific name
- `roomNumber` - Search in room numbers
- `classDay` - Filter by class day
- `classTime` - Search in class times
- `startDateFrom` / `startDateTo` - Start date range
- `studentId` - Classes containing specific student
- `studentName` - Classes containing student with specific name
- `createdFrom` / `createdTo` - Creation date range
- `isActive` - Active/inactive classes

### Example Usage:
```java
ClassFilter filter = new ClassFilter();
filter.setClassName("Mathematics");
filter.setTeacherName("john");
filter.setIsActive(true);
filter.setStartDateFrom(LocalDate.now());

ClassSpec spec = new ClassSpec(filter);
List<ClassEntity> activeMathClasses = classRepository.findAll(spec);
```

## 5. TimeTable Filter

### Filter Options:
- `scheduleId` - Specific schedule ID
- `dayOfWeek` - Filter by day (MONDAY, TUESDAY, etc.)
- `startTimeFrom` / `startTimeTo` - Start time range
- `endTimeFrom` / `endTimeTo` - End time range
- `teacherId` - Schedules of specific teacher
- `teacherName` - Schedules of teacher with specific name
- `classId` - Schedules of specific class
- `className` - Schedules of classes with specific name
- `timeSlot` - Schedules at specific time
- `isActive` - Active schedules (current time within schedule)
- `timeRange` - Time range (morning, afternoon, evening, night)

### Example Usage:
```java
TimeTableFilter filter = new TimeTableFilter();
filter.setDayOfWeek(DayOfWeek.MONDAY);
filter.setTimeRange("morning");
filter.setTeacherName("smith");
filter.setIsActive(true);

TimeTableSpec spec = new TimeTableSpec(filter);
List<TimeTable> activeMondayMorningSchedules = timeTableRepository.findAll(spec);
```

## Advanced Features

### Case-Insensitive Search
All text searches are case-insensitive and use partial matching:
```java
filter.setStudentName("john"); // Matches "John", "JOHN", "Johnson", etc.
```

### Date Range Filtering
Use date ranges for flexible time-based filtering:
```java
filter.setDateFrom(LocalDate.now().minusDays(7));
filter.setDateTo(LocalDate.now());
```

### Boolean Filters
Use boolean filters for simple yes/no conditions:
```java
filter.setIsPresent(true); // Only present students
filter.setIsActive(true); // Only active classes/teachers
```

### Age and Experience Calculations
Age and experience are calculated automatically:
```java
filter.setAgeFrom(18); // Students 18 or older
filter.setExperienceYearsFrom(5); // Teachers with 5+ years experience
```

### Time-Based Filtering
For timetables, use time ranges and active status:
```java
filter.setTimeRange("morning"); // 6 AM - 12 PM
filter.setIsActive(true); // Currently active schedules
```

## Best Practices

1. **Use specific filters** when you know exact values (IDs, specific dates)
2. **Use range filters** for flexible searches (date ranges, age ranges)
3. **Use text searches** for user input (names, descriptions)
4. **Combine multiple filters** for precise results
5. **Use boolean filters** for simple conditions (active/inactive, present/absent)

## Performance Considerations

- Filters with IDs are the fastest (exact matches)
- Text searches with wildcards are slower but more flexible
- Date range filters are efficient with proper indexing
- Joins (for related entity searches) can be slower with large datasets
- Consider adding database indexes on frequently filtered fields

## Repository Integration

All specifications can be used with Spring Data JPA repositories:

```java
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    // Your custom methods here
}
```

The `JpaSpecificationExecutor<Attendance>` interface provides the `findAll(Specification<T>)` method needed to use the specifications. 