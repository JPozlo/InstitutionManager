package com.example.institutionmanager.service;

import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.CourseRequestDto;
import com.example.institutionmanager.common.dto.CourseResponseDto;
import com.example.institutionmanager.common.exception.NameAlreadyTakenException;
import com.example.institutionmanager.common.exception.ResourceNotFoundException;
import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.data.repository.CourseRepository;
import com.example.institutionmanager.data.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    public CourseResponseDto createCourse(Long institutionId, CourseRequestDto courseRequestDto) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        if(institution.isPresent()){
            List<Course> institutionCourses = courseRepository.findByInstitutionId(institutionId);
            boolean nameExists = institutionCourses.stream().anyMatch(course -> course.getName().equals(courseRequestDto.getName()));
            if(nameExists){
                throw new NameAlreadyTakenException("Course with the same name already exists");
            } else{
                Course course = new Course();
                course.setInstitution(institution.get());
                course.setName(courseRequestDto.getName());
                Course savedCourse = courseRepository.save(course);
                return new CourseResponseDto(savedCourse.getId(), savedCourse.getName(), savedCourse.getInstitution());
            }
        } else{
            throw new ResourceNotFoundException("Institution with the id: " + institutionId +" not found");
        }
    }

    @Override
    public CourseResponseDto updateCourseName(Long institutionId, Long courseId, String name) {
            List<Course> institutionCourses = courseRepository.findByInstitutionId(institutionId);
            boolean nameExists = institutionCourses.stream().anyMatch(course -> course.getName().equals(name));
            if(nameExists){
                throw new NameAlreadyTakenException("Course with the same name already exists");
            } else{
                Optional<Course> optionalCourse = courseRepository.findById(courseId);
                if(optionalCourse.isPresent()){
                    optionalCourse.get().setName(name);
                    Course updatedCourse = courseRepository.save(optionalCourse.get());
                    return new CourseResponseDto(updatedCourse.getId(), updatedCourse.getName(), updatedCourse.getInstitution());
                } else{
                    throw new ResourceNotFoundException("Course with the id of " + courseId + " not found");
                }
            }
    }

    @Override
    public List<CourseResponseDto> filterByName(String name) {
        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
        courseRepository.findByName(name).forEach(course -> {
            CourseResponseDto courseResponseDto = new CourseResponseDto(course.getId(), course.getName(), course.getInstitution());
            courseResponseDtoList.add(courseResponseDto);
        });
        return courseResponseDtoList;
    }

    @Override
    public ApiResponse deleteStudent(Long courseId) {
        Optional<Course> course  = courseRepository.findById(courseId);
        if(course.isPresent()){
            courseRepository.delete(course.get());
            return new ApiResponse("Course successfully deleted");
        } else{
            throw new ResourceNotFoundException("Course with the id of " + courseId + " not found");
        }
    }

    @Override
    public List<CourseResponseDto> getAllCoursesByInstitution(Long institutionId) {
        List<Course> institutionCourses = courseRepository.findByInstitutionId(institutionId);
        if(institutionCourses.isEmpty()){
            throw new ResourceNotFoundException("There are no courses available yet");
        } else{
            List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
            institutionCourses.forEach(course -> {
                CourseResponseDto courseResponseDto = new CourseResponseDto(course.getId(), course.getName(), course.getInstitution());
                courseResponseDtoList.add(courseResponseDto);
            });
            return courseResponseDtoList;
        }

    }

    @Override
    public List<CourseResponseDto> getAllSortByNameAscending() {
        List<Course> institutionCourses = courseRepository.findAllSortByNameAscending();
        if(institutionCourses.isEmpty()){
            throw new ResourceNotFoundException("There are no courses available yet");
        } else{
            List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
            institutionCourses.forEach(course -> {
                CourseResponseDto courseResponseDto = new CourseResponseDto(course.getId(), course.getName(), course.getInstitution());
                courseResponseDtoList.add(courseResponseDto);
            });
            return courseResponseDtoList;
        }
    }

    @Override
    public List<CourseResponseDto> getAllSortByNameDescending() {
        List<Course> institutionCourses = courseRepository.findAllSortByNameDescending();
        if(institutionCourses.isEmpty()){
            throw new ResourceNotFoundException("There are no courses available yet");
        } else{
            List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
            institutionCourses.forEach(course -> {
                CourseResponseDto courseResponseDto = new CourseResponseDto(course.getId(), course.getName(), course.getInstitution());
                courseResponseDtoList.add(courseResponseDto);
            });
            return courseResponseDtoList;
        }

    }
}
