# Attendance API Guide - Bulk Attendance System

## Overview
This guide explains how to implement the bulk attendance system for teachers. The system allows teachers to mark attendance for all students in a class at once, with all students pre-filled as PRESENT by default.

## API Endpoints

### 1. Get Students for Attendance Initialization
**GET** `/attendances/class/{classId}/students`

This endpoint returns all students in a class with default PRESENT status. Use this when the teacher first opens the attendance page.

**Response:**
```json
[
  {
    "studentName": "John Doe",
    "className": "Class 10A",
    "teacherName": "Jane Smith",
    "date": "2024-01-15",
    "status": "PRESENT",
    "remark": ""
  }
]
```

### 2. Check Existing Attendance
**GET** `/attendances/class/{classId}/date/{date}`

Check if attendance has already been marked for a specific class and date. This helps determine whether to use the create or update endpoint.

**Response:**
- Empty array `[]` = No attendance marked yet
- Array with attendance records = Attendance already exists

### 3. Mark Bulk Attendance (Create)
**POST** `/attendances/bulk`

Use this endpoint when marking attendance for the first time on a given date.

**Request Body:**
```json
{
  "classId": 1,
  "attendaneList": [
    {
      "studentId": 1,
      "status": "PRESENT",
      "remark": ""
    },
    {
      "studentId": 2,
      "status": "ABSENT",
      "remark": "Sick"
    },
    {
      "studentId": 3,
      "status": "LATE",
      "remark": "Traffic"
    }
  ]
}
```

**Response:**
Returns the created attendance records.

### 4. Update Bulk Attendance
**PUT** `/attendances/bulk`

Use this endpoint when updating existing attendance records.

**Request Body:** Same as POST endpoint.

## Frontend Implementation Flow

### Step 1: Load Attendance Page
1. Call `GET /attendances/class/{classId}/students` to get all students
2. Pre-fill the attendance form with all students marked as PRESENT
3. Display the form to the teacher

### Step 2: Check for Existing Attendance
1. Call `GET /attendances/class/{classId}/date/{currentDate}`
2. If records exist:
   - Load existing attendance data
   - Allow teacher to modify
   - Use PUT endpoint for updates
3. If no records exist:
   - Use POST endpoint for creation

### Step 3: Save Attendance
1. Collect all attendance statuses from the form
2. Send to appropriate endpoint (POST or PUT)
3. Handle success/error responses

## Attendance Status Values
- `PRESENT` - Student is present
- `ABSENT` - Student is absent
- `LATE` - Student arrived late

## Error Handling
- **400 Bad Request**: Invalid data or business rule violations
- **404 Not Found**: Class or student not found
- **409 Conflict**: Attendance already marked for the day

## Example Frontend Code (JavaScript)

```javascript
class AttendanceManager {
  constructor(classId) {
    this.classId = classId;
    this.currentDate = new Date().toISOString().split('T')[0];
  }

  async loadAttendancePage() {
    try {
      // Get students for initialization
      const students = await this.getStudentsForAttendance();
      
      // Check if attendance already exists
      const existingAttendance = await this.getExistingAttendance();
      
      if (existingAttendance.length > 0) {
        // Load existing data
        this.populateFormWithExistingData(existingAttendance);
      } else {
        // Initialize with default PRESENT status
        this.populateFormWithDefaultData(students);
      }
    } catch (error) {
      console.error('Error loading attendance page:', error);
    }
  }

  async saveAttendance(attendanceData) {
    try {
      const existingAttendance = await this.getExistingAttendance();
      const endpoint = existingAttendance.length > 0 ? '/bulk' : '/bulk';
      const method = existingAttendance.length > 0 ? 'PUT' : 'POST';
      
      const response = await fetch(`/attendances${endpoint}`, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          classId: this.classId,
          attendaneList: attendanceData
        })
      });
      
      if (response.ok) {
        alert('Attendance saved successfully!');
      } else {
        throw new Error('Failed to save attendance');
      }
    } catch (error) {
      console.error('Error saving attendance:', error);
      alert('Failed to save attendance. Please try again.');
    }
  }

  async getStudentsForAttendance() {
    const response = await fetch(`/attendances/class/${this.classId}/students`);
    return await response.json();
  }

  async getExistingAttendance() {
    const response = await fetch(`/attendances/class/${this.classId}/date/${this.currentDate}`);
    return await response.json();
  }

  populateFormWithDefaultData(students) {
    // Implementation to populate form with default PRESENT status
  }

  populateFormWithExistingData(existingAttendance) {
    // Implementation to populate form with existing attendance data
  }
}
```

## Notes
- All students are pre-filled as PRESENT by default
- Teachers can change any status to ABSENT or LATE
- The system prevents duplicate attendance records for the same day
- Use the appropriate endpoint based on whether attendance exists or not
- Always include the classId in the request body 