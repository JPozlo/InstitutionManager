package com.example.institutionmanager.service;

import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.StudentPaginatedResponseDto;
import com.example.institutionmanager.common.dto.StudentRequestDto;
import com.example.institutionmanager.common.dto.StudentResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {
    StudentResponseDto createStudent(Long institutionId, StudentRequestDto studentRequestDto);
    StudentResponseDto updateStudentName(Long studentId, String name);
    ApiResponse deleteStudent(Long studentId);
    List<StudentPaginatedResponseDto> getAllStudentsByInstitution(Long institutionId, Pageable pageable);
    StudentResponseDto updateStudentInstitution(Long institutionId, Long studentId);
    StudentResponseDto addStudentCourse(Long courseId, Long studentId);
}
