package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jaydee.SchoolManagement.dto.ClassRequestDTO;
import com.jaydee.SchoolManagement.dto.ClassResponseDTO;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.entity.Teacher;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.ClassMapper;
import com.jaydee.SchoolManagement.repository.ClassRepository;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.repository.TeacherRepository;
import com.jaydee.SchoolManagement.service.ClassService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ClassMapper classMapper;

    @Override
    public ClassResponseDTO create(ClassRequestDTO dto) {
        ClassEntity entity = classMapper.toEntity(dto);
        // Do not set teacher or students here
        ClassEntity saved = classRepository.save(entity);
        return classMapper.toResponseDTO(saved);
    }

    @Override
    public ClassResponseDTO getById(Long classId) {
        ClassEntity entity = classRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFound("Class", classId));
        return classMapper.toResponseDTO(entity);
    }

    @Override
    public List<ClassResponseDTO> getAll() {
        return classRepository.findAll().stream()
            .map(classMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ClassResponseDTO updateClass(Long classId, ClassRequestDTO dto) {
        ClassEntity entity = classRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFound("Class not found"));
        entity.setClassName(dto.getClassName());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setClassDay(dto.getClassDay());
        entity.setClassTime(dto.getClassTime());
        entity.setStartDate(dto.getStartDate());
        // Do not set teacher or students here
        ClassEntity saved = classRepository.save(entity);
        return classMapper.toResponseDTO(saved);
    }

    @Override
    public void deleteClass(Long classId) {
    	if(!classRepository.existsById(classId)) {
    		throw new ResourceNotFound("Class", classId);
    	}
        classRepository.deleteById(classId);
    }

    @Override
    public ClassResponseDTO assignTeacher(Long classId, Long teacherId) {
        ClassEntity entity = classRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFound("Class not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new ResourceNotFound("Teacher not found"));
        entity.setTeacher(teacher);
        ClassEntity saved = classRepository.save(entity);
        return classMapper.toResponseDTO(saved);
    }

    @Override
    public ClassResponseDTO assignStudent(Long classId, Long studentId) {
        ClassEntity entity = classRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFound("Class not found"));
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFound("Student not found"));
        entity.getStudents().add(student);
        ClassEntity saved = classRepository.save(entity);
        return classMapper.toResponseDTO(saved);
    }

	@Override
	public ClassResponseDTO removeStudentFromClass(Long classId, Long studentId) {
		ClassEntity classEntity = classRepository.findById(classId)
				.orElseThrow(()-> new ResourceNotFound("Class not found"));
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new ResourceNotFound("Student", studentId));
		classEntity.getStudents().remove(student);
		classRepository.save(classEntity);
		return classMapper.toResponseDTO(classEntity);
	}

//	@Override
//	public ClassResponseDTO removeTeacherFromClass(Long classId, Long teacherId) {
//		ClassEntity classEntity = classRepository.findById(classId)
//				.orElseThrow(()-> new ResourceNotFound("Class not found"));
//		Teacher teacher = teacherRepository.findById(teacherId)
//				.orElseThrow(()-> new ResourceNotFound("Teacher", teacherId));
//		//classEntity.getTeacher()
//		classRepository.save(classEntity);
//		return classMapper.toResponseDTO(classEntity);
//	}
} 