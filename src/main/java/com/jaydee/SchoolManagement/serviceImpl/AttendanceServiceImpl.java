package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.AttendanceRequestDTO;
import com.jaydee.SchoolManagement.dto.AttendanceResponseDTO;
import com.jaydee.SchoolManagement.dto.BulkAttendanceSubmitRequest;
import com.jaydee.SchoolManagement.entity.Attendance;
import com.jaydee.SchoolManagement.entity.AttendanceStatus;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.AttendanceMapper;
import com.jaydee.SchoolManagement.repository.AttendanceRepository;
import com.jaydee.SchoolManagement.repository.ClassRepository;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.repository.TeacherRepository;
import com.jaydee.SchoolManagement.service.AttendanceService;
import com.jaydee.SchoolManagement.specification.AttendanceFilter;
import com.jaydee.SchoolManagement.specification.AttendanceSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO requestDTO) {
        Attendance attendance = new Attendance();
        attendance.setStudent(studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found")));
        attendance.setClassEntity(classRepository.findById(requestDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFound("Class not found")));
                
        // Check for existing attendance records for the class and date
        List<Attendance> existingAttendances = attendanceRepository
                .findExistingAttendanceByClassAndDate(requestDTO.getClassId(), requestDTO.getDate());
        
        if (!existingAttendances.isEmpty()) {
            throw new IllegalStateException("Attendance records already exist for this class and date");
        }

        // Create attendance records for each student
        List<Attendance> attendances = requestDTO.getAttendances().stream()
                .map(dto -> {
                    Attendance attendance = new Attendance();
                    attendance.setDate(request.getDate());
                    attendance.setStatus(dto.getStatus());
                    attendance.setRemark(dto.getRemark());
                    attendance.setStudent(studentRepository.findById(dto.getStudentId())
                            .orElseThrow(() -> new ResourceNotFound("Student not found")));
                    attendance.setClassEntity(classEntity);
                    attendance.setRecordBy(teacherRepository.findById(teacherId)
                            .orElseThrow(() -> new ResourceNotFound("Teacher not found")));
                    return attendance;
                })
                .collect(Collectors.toList());

        List<Attendance> savedAttendances = attendanceRepository.saveAll(attendances);
        return savedAttendances.stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO updateStatus(Long attendanceId, AttendanceStatus status, String remarks, Long teacherId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFound("Attendance not found"));
        
        attendance.setStatus(status);
        attendance.setRemark(remarks);
        attendance.setRecordBy(teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFound("Teacher not found")));
        
        Attendance updated = attendanceRepository.save(attendance);
        return attendanceMapper.toResponseDTO(updated);
    }


    @Override
    public List<AttendanceResponseDTO> getTodayAttendanceForClass(Long classId, LocalDate date) {
        List<Attendance> attendances = attendanceRepository.findExistingAttendanceByClassAndDate(classId, date);
        return attendances.stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendance not found"));
        attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAttendanceByStudentFullName(String fullName) {
        return attendanceRepository.findByStudentFullName(fullName)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
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

//    @Override
//    public List<AttendanceResponseDTO> getAttendanceByStudentName(String name) {
//        return attendanceRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(name, name)
//                .stream()
//                .map(attendanceMapper::toResponseDTO)
//                .collect(Collectors.toList());
//    }


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

    // New JPA Specification methods
    @Override
    public List<AttendanceResponseDTO> findAttendancesByFilter(AttendanceFilter filter) {
        AttendanceSpec spec = new AttendanceSpec(filter);
        return attendanceRepository.findAll(spec)
                .stream()
                .map(attendanceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByStudentName(String studentName) {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setStudentName(studentName);
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByDateRange(LocalDate dateFrom, LocalDate dateTo) {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByStatus(String status) {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setStatus(AttendanceStatus.valueOf(status.toUpperCase()));
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findPresentAttendances() {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setIsPresent(true);
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findAbsentAttendances() {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setIsPresent(false);
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByTeacherName(String teacherName) {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setTeacherName(teacherName);
        return findAttendancesByFilter(filter);
    }

    @Override
    public List<AttendanceResponseDTO> findAttendancesByClassAndDateRange(String className, LocalDate dateFrom, LocalDate dateTo) {
        AttendanceFilter filter = new AttendanceFilter();
        filter.setClassName(className);
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        return findAttendancesByFilter(filter);
    }

	@Override
	public AttendanceResponseDTO updateStudentAttendance(Long attendanceId, AttendanceStatus status, String remarks) {
		Attendance attendance = attendanceRepository.findById(attendanceId)
				.orElseThrow(() -> new RuntimeException("Attendance record not found"));
		
		attendance.setStatus(status);
		attendance.setRemark(remarks);
		
		Attendance updated = attendanceRepository.save(attendance);
		return attendanceMapper.toResponseDTO(updated);
	}


} 