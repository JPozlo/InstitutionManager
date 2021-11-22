package com.example.institutionmanager.service;

import com.example.institutionmanager.common.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseService {
    CourseResponseDto createCourse(Long institutionId, CourseRequestDto courseRequestDto);
    CourseResponseDto updateCourseName(Long institutionId, Long courseId, String name);
    List<CourseResponseDto> filterByName(String name);
    ApiResponse deleteCourse(Long courseId);
    List<CourseResponseDto> getAllCoursesByInstitution(Long institutionId);
    List<CourseResponseDto> getAllSortByNameAscending();
    List<CourseResponseDto> getAllSortByNameDescending();
}
