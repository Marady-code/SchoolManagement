package com.jaydee.SchoolManagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.ExamRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResponseDTO;
import com.jaydee.SchoolManagement.entity.ClassEntity;
import com.jaydee.SchoolManagement.entity.Exam;
import com.jaydee.SchoolManagement.entity.Subject;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.ExamMapper;
import com.jaydee.SchoolManagement.repository.ClassRepository;
import com.jaydee.SchoolManagement.repository.ExamRepository;
import com.jaydee.SchoolManagement.repository.SubjectRepository;
import com.jaydee.SchoolManagement.service.ExamService;
import com.jaydee.SchoolManagement.specification.ExamFilter;
import com.jaydee.SchoolManagement.specification.ExamSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {
    
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;

    @Override
    public ExamResponseDTO createExam(ExamRequestDTO requestDTO) {
        Subject subject = subjectRepository.findById(requestDTO.getSubjectId())
                .orElseThrow(() -> new ResourceNotFound("Subject not found"));
        
        ClassEntity classEntity = classRepository.findById(requestDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFound("Class not found"));
        
        Exam exam = examMapper.toEntity(requestDTO);
        exam.setSubject(subject);
        exam.setClassEntity(classEntity);
        
        Exam saved = examRepository.save(exam);
        return examMapper.toResponseDTO(saved);
    }

    @Override
    public ExamResponseDTO updateExam(Long id, ExamRequestDTO requestDTO) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam not found"));
        
        Subject subject = subjectRepository.findById(requestDTO.getSubjectId())
                .orElseThrow(() -> new ResourceNotFound("Subject not found"));
        
        ClassEntity classEntity = classRepository.findById(requestDTO.getClassId())
                .orElseThrow(() -> new ResourceNotFound("Class not found"));
        
        exam.setExamTitle(requestDTO.getExamTitle());
        exam.setDescription(requestDTO.getDescription());
        exam.setSubject(subject);
        exam.setClassEntity(classEntity);
        exam.setExamDate(requestDTO.getExamDate());
        exam.setStartTime(requestDTO.getStartTime());
        exam.setEndTime(requestDTO.getEndTime());
        exam.setTotalMarks(requestDTO.getTotalMarks());
        exam.setExamType(requestDTO.getExamType());
        
        Exam updated = examRepository.save(exam);
        return examMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam not found"));
        examRepository.delete(exam);
    }

    @Override
    public ExamResponseDTO getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam not found"));
        return examMapper.toResponseDTO(exam);
    }

    @Override
    public List<ExamResponseDTO> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsByFilter(ExamFilter filter) {
        ExamSpec spec = new ExamSpec(filter);
        return examRepository.findAll(spec)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsBySubject(Long subjectId) {
        return examRepository.findBySubjectSubjectId(subjectId)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsByClass(Long classId) {
        return examRepository.findByClassEntityClassId(classId)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsByDate(LocalDate examDate) {
        return examRepository.findByExamDate(examDate)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsByDateRange(LocalDate dateFrom, LocalDate dateTo) {
        return examRepository.findByExamDateBetween(dateFrom, dateTo)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponseDTO> findExamsByType(String examType) {
        return examRepository.findByExamType(examType)
                .stream()
                .map(examMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
} 