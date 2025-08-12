package com.jaydee.SchoolManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaydee.SchoolManagement.dto.ExamResultRequestDTO;
import com.jaydee.SchoolManagement.dto.ExamResultResponseDTO;
import com.jaydee.SchoolManagement.entity.Exam;
import com.jaydee.SchoolManagement.entity.ExamResult;
import com.jaydee.SchoolManagement.entity.Student;
import com.jaydee.SchoolManagement.exception.ResourceNotFound;
import com.jaydee.SchoolManagement.mapper.ExamResultMapper;
import com.jaydee.SchoolManagement.repository.ExamRepository;
import com.jaydee.SchoolManagement.repository.ExamResultRepository;
import com.jaydee.SchoolManagement.repository.StudentRepository;
import com.jaydee.SchoolManagement.service.ExamResultService;
import com.jaydee.SchoolManagement.specification.ExamResultFilter;
import com.jaydee.SchoolManagement.specification.ExamResultSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamResultServiceImpl implements ExamResultService {
    
    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Override
    public ExamResultResponseDTO createExamResult(ExamResultRequestDTO requestDTO) {
        Exam exam = examRepository.findById(requestDTO.getExamId())
                .orElseThrow(() -> new ResourceNotFound("Exam not found"));
        
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found"));
        
        // Check if result already exists for this student and exam
        boolean exists = examResultRepository.findByStudentStudentIdAndExamExamId(
            requestDTO.getStudentId(), requestDTO.getExamId()).size() > 0;
        if (exists) {
            throw new IllegalArgumentException("Exam result already exists for this student and exam");
        }
        
        ExamResult examResult = examResultMapper.toEntity(requestDTO);
        examResult.setExam(exam);
        examResult.setStudent(student);
        
        // Calculate percentage and grade
        calculatePercentageAndGrade(examResult, exam);
        
        ExamResult saved = examResultRepository.save(examResult);
        return examResultMapper.toResponseDTO(saved);
    }

    @Override
    public ExamResultResponseDTO updateExamResult(Long id, ExamResultRequestDTO requestDTO) {
        ExamResult examResult = examResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam result not found"));
        
        Exam exam = examRepository.findById(requestDTO.getExamId())
                .orElseThrow(() -> new ResourceNotFound("Exam not found"));
        
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFound("Student not found"));
        
        examResult.setExam(exam);
        examResult.setStudent(student);
        examResult.setObtainedMarks(requestDTO.getObtainedMarks());
        examResult.setRemarks(requestDTO.getRemarks());
        
        // Recalculate percentage and grade
        calculatePercentageAndGrade(examResult, exam);
        
        ExamResult updated = examResultRepository.save(examResult);
        return examResultMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteExamResult(Long id) {
        ExamResult examResult = examResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam result not found"));
        examResultRepository.delete(examResult);
    }

    @Override
    public ExamResultResponseDTO getExamResultById(Long id) {
        ExamResult examResult = examResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Exam result not found"));
        return examResultMapper.toResponseDTO(examResult);
    }

    @Override
    public List<ExamResultResponseDTO> getAllExamResults() {
        return examResultRepository.findAll()
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsByFilter(ExamResultFilter filter) {
        ExamResultSpec spec = new ExamResultSpec(filter);
        return examResultRepository.findAll(spec)
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsByStudent(Long studentId) {
        return examResultRepository.findByStudentStudentId(studentId)
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsByExam(Long examId) {
        return examResultRepository.findByExamExamId(examId)
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsBySubject(Long subjectId) {
        return examResultRepository.findByExamSubjectSubjectId(subjectId)
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsByGrade(String grade) {
        return examResultRepository.findByGrade(grade)
                .stream()
                .map(examResultMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResultResponseDTO> findExamResultsByPercentageRange(Double minPercentage, Double maxPercentage) {
        if (minPercentage != null && maxPercentage != null) {
            return examResultRepository.findByPercentageGreaterThanEqualAndPercentageLessThanEqual(minPercentage, maxPercentage)
                    .stream()
                    .map(examResultMapper::toResponseDTO)
                    .collect(Collectors.toList());
        } else if (minPercentage != null) {
            return examResultRepository.findByPercentageGreaterThanEqual(minPercentage)
                    .stream()
                    .map(examResultMapper::toResponseDTO)
                    .collect(Collectors.toList());
        } else if (maxPercentage != null) {
            return examResultRepository.findByPercentageLessThan(maxPercentage)
                    .stream()
                    .map(examResultMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        return getAllExamResults();
    }
    
    private void calculatePercentageAndGrade(ExamResult examResult, Exam exam) {
        if (examResult.getObtainedMarks() != null && exam.getTotalMarks() != null && exam.getTotalMarks() > 0) {
            double percentage = (examResult.getObtainedMarks() / exam.getTotalMarks()) * 100;
            examResult.setPercentage(percentage);
            
            // Calculate grade based on percentage
            if (percentage >= 90) {
                examResult.setGrade("A+");
            } else if (percentage >= 80) {
                examResult.setGrade("A");
            } else if (percentage >= 70) {
                examResult.setGrade("B");
            } else if (percentage >= 60) {
                examResult.setGrade("C");
            } else if (percentage >= 50) {
                examResult.setGrade("D");
            } else {
                examResult.setGrade("F");
            }
        }
    }
} 