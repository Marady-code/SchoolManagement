package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.TimeTableRequestDTO;
import com.jaydee.SchoolManagement.dto.TimeTableResponseDTO;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import com.jaydee.SchoolManagement.entity.DayOfWeek;
import com.jaydee.SchoolManagement.entity.Subject;
import com.jaydee.SchoolManagement.entity.Teacher;
import com.jaydee.SchoolManagement.entity.TimeTable;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.TimeTableMapper;
import com.jaydee.SchoolManagement.repository.ClassRepository;
import com.jaydee.SchoolManagement.repository.SubjectRepository;
import com.jaydee.SchoolManagement.repository.TeacherRepository;
import com.jaydee.SchoolManagement.repository.TimeTableRepository;
import com.jaydee.SchoolManagement.service.TimeTableService;
import com.jaydee.SchoolManagement.specification.TimeTableFilter;
import com.jaydee.SchoolManagement.specification.TimeTableSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;
    private final TimeTableMapper timeTableMapper;
    private final SubjectRepository subjectRepository;
    
    @Override
    public TimeTableResponseDTO createSchedule(TimeTableRequestDTO request) {
    	
    	if(timeTableRepository.existsClassConflict(request.getClassId(), request.getDayOfWeek(), request.getStartTime(), request.getEndTime())) {
            throw new IllegalArgumentException("Class already has a schedule in this time slot");
        }
        if(timeTableRepository.existsTeacherConflict(request.getTeacherId(), request.getDayOfWeek(), request.getStartTime(), request.getEndTime())) {
            throw new IllegalArgumentException("Teacher already assigned to another class in this time slot");
        }
    	
    	ClassEntity classEntity = classRepository.findById(request.getClassId())
    			.orElseThrow(() -> new ResourceNotFound("Class not found"));
    	Subject subject = subjectRepository.findById(request.getSubjectId())
    			.orElseThrow(() -> new ResourceNotFound("Subject not found"));
    	Teacher teacher = teacherRepository.findById(request.getTeacherId())
    			.orElseThrow(() -> new ResourceNotFound("Teacher not found"));
    	
    	TimeTable timeTable = timeTableMapper.toEntity(request);
    	timeTable.setClassEntity(classEntity);
    	timeTable.setDayOfWeek(request.getDayOfWeek());
    	timeTable.setSubject(subject);
    	timeTable.setTeacher(teacher);
    	timeTable.setRoom(request.getRoom());
    	timeTable.setStartTime(request.getStartTime());
    	timeTable.setEndTime(request.getEndTime());
        return timeTableMapper.toResponseDTO(timeTableRepository.save(timeTable));
    }

    @Override
    public TimeTableResponseDTO updateSchedule(Long scheduleId, TimeTableRequestDTO requestUpdateDTO) {
        TimeTable timeTable = timeTableRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFound("Schedule", scheduleId));
        
        timeTableMapper.updateTimeTableFromDto(requestUpdateDTO, timeTable);
        TimeTable updated = timeTableRepository.save(timeTable);
        
        return timeTableMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        if (!timeTableRepository.existsById(scheduleId)) {
            throw new ResourceNotFound("Schedule", scheduleId);
        }
        timeTableRepository.deleteById(scheduleId);
    }

    // JPA Specification methods
    @Override
    public List<TimeTableResponseDTO> findSchedulesByFilter(TimeTableFilter filter) {
        TimeTableSpec spec = new TimeTableSpec(filter);
        return timeTableRepository.findAll(spec)
        		.stream()
        		.map(timeTableMapper::toResponseDTO)
        		.collect(Collectors.toList());
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByDay(DayOfWeek dayOfWeek) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setDayOfWeek(dayOfWeek);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByTeacher(String teacherName) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTeacherName(teacherName);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByTeacherId(Long teacherId) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTeacherId(teacherId);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByClass(String className) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setClassName(className);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByClassId(Long classId) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setClassId(classId);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByTimeRange(LocalTime startTime, LocalTime endTime) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setStartTimeFrom(startTime);
        filter.setStartTimeTo(endTime);
        return findSchedulesByFilter(filter);
    }

    @Override
    public List<TimeTableResponseDTO> findSchedulesByTimeRange(String timeRange) {
        TimeTableFilter filter = new TimeTableFilter();
        filter.setTimeRange(timeRange);
        return findSchedulesByFilter(filter);
    }
    
    
//    @Override
//    public TimeTableResponseDTO getScheduleById(Long scheduleId) {
//        return timeTableRepository.findById(scheduleId)
//                .orElseThrow(() -> new ResourceNotFound("Schedule", scheduleId));
//    }
} 