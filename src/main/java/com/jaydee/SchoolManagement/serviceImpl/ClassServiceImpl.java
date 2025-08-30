package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalDate;
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
import com.jaydee.SchoolManagement.specification.ClassFilter;
import com.jaydee.SchoolManagement.specification.ClassSpec;

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
        return classRepository.findAll()
        	.stream()
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
        if(student.getClassEntity() != null && student.getClassEntity().getClassId().equals(classId)) {
        	throw new IllegalArgumentException("This Student already assigned to this class");
        }
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
	
	// New JPA Specification methods
	@Override
	public List<ClassResponseDTO> findClassesByFilter(ClassFilter filter) {
		ClassSpec spec = new ClassSpec(filter);
		return classRepository.findAll(spec)
				.stream()
				.map(classMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<ClassResponseDTO> findClassesByName(String className) {
		ClassFilter filter = new ClassFilter();
		filter.setClassName(className);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByTeacher(String teacherName) {
		ClassFilter filter = new ClassFilter();
		filter.setTeacherName(teacherName);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByTeacherId(Long teacherId) {
		ClassFilter filter = new ClassFilter();
		filter.setTeacherId(teacherId);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByRoomNumber(String roomNumber) {
		ClassFilter filter = new ClassFilter();
		filter.setRoomNumber(roomNumber);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByDay(String classDay) {
		ClassFilter filter = new ClassFilter();
		filter.setClassDay(classDay);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByTime(String classTime) {
		ClassFilter filter = new ClassFilter();
		filter.setClassTime(classTime);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByStartDateRange(LocalDate dateFrom, LocalDate dateTo) {
		ClassFilter filter = new ClassFilter();
		filter.setStartDateFrom(dateFrom);
		filter.setStartDateTo(dateTo);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByStudent(String studentName) {
		ClassFilter filter = new ClassFilter();
		filter.setStudentName(studentName);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByStudentId(Long studentId) {
		ClassFilter filter = new ClassFilter();
		filter.setStudentId(studentId);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findActiveClasses() {
		ClassFilter filter = new ClassFilter();
		filter.setIsActive(true);
		return findClassesByFilter(filter);
	}

	@Override
	public List<ClassResponseDTO> findClassesByCreationDateRange(LocalDate dateFrom, LocalDate dateTo) {
		ClassFilter filter = new ClassFilter();
		filter.setCreatedFrom(dateFrom);
		filter.setCreatedTo(dateTo);
		return findClassesByFilter(filter);
	}
} 