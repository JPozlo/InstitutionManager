package com.example.institutionmanager.web.controller;

import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.CourseRequestDto;
import com.example.institutionmanager.common.dto.CourseResponseDto;
import com.example.institutionmanager.common.dto.CourseUpdateRequestDto;
import com.example.institutionmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/create/{institutionId}")
    public ResponseEntity<CourseResponseDto> createCourse(@PathVariable Long institutionId, @RequestBody CourseRequestDto courseRequestDto){
        CourseResponseDto courseResponseDto = courseService.createCourse(institutionId, courseRequestDto);
        return new ResponseEntity<>(courseResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseResponseDto> updateCourseName(@PathVariable Long courseId, @RequestBody CourseUpdateRequestDto courseUpdateRequestDto){
        CourseResponseDto courseResponseDto = courseService.updateCourseName(courseUpdateRequestDto.getInstitutionId(), courseId, courseUpdateRequestDto.getName());
        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long courseId){
        ApiResponse apiResponse = courseService.deleteCourse(courseId);
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter/")
    public ResponseEntity<List<CourseResponseDto>> filterCoursesByName(@RequestParam("name") String name){
        List<CourseResponseDto> courseResponseDtoList = courseService.filterByName(name);
        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all/{institutionId}")
    public ResponseEntity<List<CourseResponseDto>> getAllCoursesInInstitution(@PathVariable Long institutionId){
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllCoursesByInstitution(institutionId);
        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all/sort/asc")
    public ResponseEntity<List<CourseResponseDto>> getAllCoursesSortedInAscending(){
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllSortByNameAscending();
        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all/sort/desc")
    public ResponseEntity<List<CourseResponseDto>> getAllCoursesSortedInDescending(){
        List<CourseResponseDto> courseResponseDtoList = courseService.getAllSortByNameDescending();
        return new ResponseEntity<>(courseResponseDtoList, HttpStatus.OK);
    }

}
