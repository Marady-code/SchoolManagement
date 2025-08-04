# School Management System API Endpoints Guide

This guide documents all the REST API endpoints available in the School Management System, including the new JPA Specification filter endpoints.

## Base URL
```
http://localhost:8080
```

## 1. Attendance Endpoints

### Basic CRUD Operations
- `POST /attendances` - Mark attendance
- `GET /attendances/student/{studentId}` - Get attendance by student ID
- `GET /attendances/student/name/{name}` - Get attendance by student name
- `GET /attendances/class/{className}` - Get attendance by class name
- `GET /attendances/class/{className}/date?date={date}` - Get attendance by class and date
- `PUT /attendances/{id}` - Update attendance
- `DELETE /attendances/{id}` - Delete attendance

### JPA Specification Filter Endpoints

#### Advanced Filter
```http
POST /attendances/filter
Content-Type: application/json

{
  "studentName": "john",
  "dateFrom": "2024-01-01",
  "dateTo": "2024-12-31",
  "status": "PRESENT",
  "className": "Mathematics",
  "teacherName": "smith"
}
```

#### Individual Search Endpoints
- `GET /attendances/search/student?studentName={name}` - Search by student name (case-insensitive)
- `GET /attendances/search/date-range?dateFrom={date}&dateTo={date}` - Search by date range
- `GET /attendances/search/status/{status}` - Search by attendance status (PRESENT, ABSENT, LATE)
- `GET /attendances/present` - Get all present attendances
- `GET /attendances/absent` - Get all absent attendances
- `GET /attendances/search/teacher?teacherName={name}` - Search by teacher name
- `GET /attendances/search/class-date-range?className={name}&dateFrom={date}&dateTo={date}` - Search by class and date range

## 2. Student Endpoints

### Basic CRUD Operations
- `POST /students` - Create student
- `GET /students/{studentId}` - Get student by ID
- `GET /students` - Get all students
- `PUT /students/{studentId}` - Update student
- `DELETE /students/{studentId}` - Delete student

### JPA Specification Filter Endpoints

#### Advanced Filter
```http
POST /students/filter
Content-Type: application/json

{
  "fullName": "john",
  "ageFrom": 15,
  "ageTo": 18,
  "gender": "MALE",
  "className": "Mathematics",
  "currentPlace": "New York"
}
```

#### Individual Search Endpoints
- `GET /students/search/name?name={name}` - Search by first name
- `GET /students/search/fullname?fullName={name}` - Search by full name (case-insensitive)
- `GET /students/search/gender/{gender}` - Search by gender (MALE, FEMALE)
- `GET /students/search/age-range?ageFrom={age}&ageTo={age}` - Search by age range
- `GET /students/search/class?className={name}` - Search students in specific class
- `GET /students/search/phone?phoneNumber={number}` - Search by phone number
- `GET /students/search/place?place={place}` - Search by current place
- `GET /students/search/dob-range?dateFrom={date}&dateTo={date}` - Search by date of birth range
- `GET /students/search/created-range?dateFrom={date}&dateTo={date}` - Search by creation date range

## 3. Teacher Endpoints

### Basic CRUD Operations
- `POST /teachers` - Create teacher
- `GET /teachers/{teacherId}` - Get teacher by ID
- `GET /teachers` - Get all teachers
- `PUT /teachers/{teacherId}` - Update teacher
- `DELETE /teachers/{teacherId}` - Delete teacher

### JPA Specification Filter Endpoints

#### Advanced Filter
```http
POST /teachers/filter
Content-Type: application/json

{
  "fullName": "smith",
  "qualification": "PhD",
  "salaryFrom": 50000,
  "salaryTo": 100000,
  "experienceYearsFrom": 5,
  "isActive": true
}
```

#### Individual Search Endpoints
- `GET /teachers/search/name?name={name}` - Search by first name
- `GET /teachers/search/fullname?fullName={name}` - Search by full name (case-insensitive)
- `GET /teachers/search/gender/{gender}` - Search by gender (MALE, FEMALE)
- `GET /teachers/search/qualification?qualification={qual}` - Search by qualification
- `GET /teachers/search/salary-range?salaryFrom={amount}&salaryTo={amount}` - Search by salary range
- `GET /teachers/search/experience-range?experienceFrom={years}&experienceTo={years}` - Search by experience range
- `GET /teachers/search/joining-date-range?dateFrom={date}&dateTo={date}` - Search by joining date range
- `GET /teachers/search/age-range?ageFrom={age}&ageTo={age}` - Search by age range
- `GET /teachers/search/class?className={name}` - Search teachers teaching specific class
- `GET /teachers/search/student?studentName={name}` - Search teachers teaching specific student
- `GET /teachers/active` - Get all active teachers
- `GET /teachers/search/place?place={place}` - Search by current place
- `GET /teachers/search/phone?phoneNumber={number}` - Search by phone number

## 4. Class Endpoints

### Basic CRUD Operations
- `POST /classes` - Create class
- `GET /classes/{classId}` - Get class by ID
- `GET /classes` - Get all classes
- `PUT /classes/{classId}` - Update class
- `DELETE /classes/{classId}` - Delete class
- `POST /classes/{classId}/assign-teacher/{teacherId}` - Assign teacher to class
- `POST /classes/{classId}/assign-student/{studentId}` - Assign student to class
- `DELETE /classes/{classId}/remove-student/{studentId}` - Remove student from class

### JPA Specification Filter Endpoints

#### Advanced Filter
```http
POST /classes/filter
Content-Type: application/json

{
  "className": "Mathematics",
  "teacherName": "john",
  "isActive": true,
  "startDateFrom": "2024-01-01",
  "studentName": "smith"
}
```

#### Individual Search Endpoints
- `GET /classes/search/name?className={name}` - Search by class name
- `GET /classes/search/teacher?teacherName={name}` - Search classes by teacher name
- `GET /classes/search/teacher/{teacherId}` - Search classes by teacher ID
- `GET /classes/search/room?roomNumber={number}` - Search by room number
- `GET /classes/search/day?classDay={day}` - Search by class day
- `GET /classes/search/time?classTime={time}` - Search by class time
- `GET /classes/search/start-date-range?dateFrom={date}&dateTo={date}` - Search by start date range
- `GET /classes/search/student?studentName={name}` - Search classes containing specific student
- `GET /classes/search/student/{studentId}` - Search classes containing specific student ID
- `GET /classes/active` - Get all active classes
- `GET /classes/search/created-range?dateFrom={date}&dateTo={date}` - Search by creation date range

## 5. Schedule/TimeTable Endpoints

### Basic CRUD Operations
- `GET /schedules` - Get all schedules
- `GET /schedules/{scheduleId}` - Get schedule by ID
- `POST /schedules` - Create schedule
- `PUT /schedules/{scheduleId}` - Update schedule
- `DELETE /schedules/{scheduleId}` - Delete schedule

### JPA Specification Filter Endpoints

#### Advanced Filter
```http
POST /schedules/filter
Content-Type: application/json

{
  "dayOfWeek": "MONDAY",
  "timeRange": "morning",
  "teacherName": "smith",
  "isActive": true
}
```

#### Individual Search Endpoints
- `GET /schedules/search/day/{dayOfWeek}` - Search by day of week (MONDAY, TUESDAY, etc.)
- `GET /schedules/search/teacher?teacherName={name}` - Search by teacher name
- `GET /schedules/search/teacher/{teacherId}` - Search by teacher ID
- `GET /schedules/search/class?className={name}` - Search by class name
- `GET /schedules/search/class/{classId}` - Search by class ID
- `GET /schedules/search/time-range?startTime={time}&endTime={time}` - Search by time range
- `GET /schedules/search/time-slot?timeSlot={time}` - Search schedules at specific time
- `GET /schedules/active` - Get all active schedules
- `GET /schedules/search/time-range-category?timeRange={category}` - Search by time category (morning, afternoon, evening, night)

## Request/Response Examples

### Example 1: Find Students by Age Range
```http
GET /students/search/age-range?ageFrom=15&ageTo=18
```

Response:
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "gender": "MALE",
    "age": 16,
    "currentPlace": "New York"
  }
]
```

### Example 2: Find Attendance by Student Name
```http
GET /attendances/search/student?studentName=john
```

Response:
```json
[
  {
    "attendanceId": 1,
    "studentName": "John Doe",
    "className": "Mathematics",
    "date": "2024-01-15",
    "status": "PRESENT",
    "teacherName": "Dr. Smith"
  }
]
```

### Example 3: Advanced Filter for Teachers
```http
POST /teachers/filter
Content-Type: application/json

{
  "qualification": "PhD",
  "salaryFrom": 60000,
  "experienceYearsFrom": 3,
  "isActive": true
}
```

Response:
```json
[
  {
    "teacherId": 1,
    "firstName": "Jane",
    "lastName": "Smith",
    "qualification": "PhD",
    "salary": 75000,
    "experienceYears": 5,
    "isActive": true
  }
]
```

## Error Responses

All endpoints return standard HTTP status codes:
- `200 OK` - Success
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid request data
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

Error response format:
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with id: 999",
  "path": "/students/999"
}
```

## Notes

1. **Case-Insensitive Search**: All text searches are case-insensitive
2. **Date Format**: Use ISO date format (YYYY-MM-DD) for all date parameters
3. **Time Format**: Use ISO time format (HH:MM:SS) for time parameters
4. **Pagination**: Currently not implemented, all endpoints return all matching results
5. **Filtering**: Multiple filters can be combined in the POST /filter endpoints
6. **Performance**: For large datasets, consider using specific filter endpoints rather than the general filter endpoint 