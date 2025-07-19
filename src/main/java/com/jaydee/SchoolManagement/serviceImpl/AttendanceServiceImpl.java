package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;
import com.jaydee.SchoolManagement.entity.Attendance;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.entity.Teacher;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.AttendanceMapper;
import com.jaydee.SchoolManagement.repository.AttendanceRepository;
import com.jaydee.SchoolManagement.repository.ClassRepository;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @Override
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO requestDTO) {
    	
    	Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found"));
    	
    	ClassEntity classEntity = classRepository.findById(requestDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFound("Class not found"));
    	
    	Teacher teacher = classEntity.getTeacher();
    	
    	// Prevent duplicate attendance
        boolean exists = attendanceRepository.existsByStudentStudentIdAndClassEntityClassIdAndDate(
            requestDTO.getStudentId(), requestDTO.getClassId(), requestDTO.getDate()
        );
        if (exists) {
            throw new IllegalArgumentException("Attendance already marked for this student, class, and date.");
        }

    	Attendance attendance = attendanceMapper.toEntity(requestDTO);
    	attendance.setStudent(student);
    	attendance.setClassEntity(classEntity);
    	attendance.setRecordBy(teacher);
    	
    	Attendance saved = attendanceRepository.save(attendance);
        return attendanceMapper.toResponseDTO(saved);
    }

    @Override
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO requestDTO) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendance not found"));
        attendance.setStudent(studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found")));
        attendance.setClassEntity(classRepository.findById(requestDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFound("Class not found")));
        attendance.setDate(requestDTO.getDate());
        attendance.setStatus(requestDTO.getStatus());
        attendance.setRemark(requestDTO.getRemark());
        // For recordBy, you may want to get the teacher from context or request
        //attendance.setRecordBy(null);
        Attendance updated = attendanceRepository.save(attendance);
        return attendanceMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendance not found"));
        attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByStudentId(Long studentId) {
        // Check if the student exists
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFound("Student not found");
        }
        // Fetch attendance records for the student
        return attendanceRepository.findByStudentStudentId(studentId)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByStudentName(String name) {
        return attendanceRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByClassName(String className) {
        return attendanceRepository.findByClassEntityClassNameContainingIgnoreCase(className)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByClassNameAndDate(String className, java.time.LocalDate date) {
        return attendanceRepository.findByClassEntityClassNameAndDate(className, date)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


//    @Override
//    public List<AttendanceResponseDTO> findAll(org.springframework.data.jpa.domain.Specification<com.jaydee.SchoolManagement.entity.Attendance> spec) {
//        return attendanceRepository.findAll(spec)
//                .stream()
//                .map(attendanceMapper::toResponseDTO)
//                .collect(Collectors.toList());
//    }
} 