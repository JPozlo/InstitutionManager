package com.example.institutionmanager.web.controller;

import com.example.institutionmanager.common.dto.*;
import com.example.institutionmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/create/{institutionId}")
    public ResponseEntity<StudentResponseDto> createStudent(@PathVariable Long institutionId, @RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto studentResponseDto = studentService.createStudent(institutionId,studentRequestDto);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudentName(@PathVariable Long studentId, @RequestBody UpdateStudentRequestDto updateStudentRequestDto){
        StudentResponseDto studentResponseDto = studentService.updateStudentName(studentId, updateStudentRequestDto.getName());
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long studentId){
        ApiResponse apiResponse = studentService.deleteStudent(studentId);
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/{institutionId}")
    public ResponseEntity<List<StudentPaginatedResponseDto>> getAllStudentsByInstitution(@PathVariable Long institutionId, @PageableDefault(page = 0, size = 10) Pageable pageable){
        List<StudentPaginatedResponseDto> studentPaginatedResponseDtoList = studentService.getAllStudentsByInstitution(institutionId, pageable);
        return new ResponseEntity<>(studentPaginatedResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/update-institution/{studentId}/{institutionId}")
    public ResponseEntity<StudentResponseDto> updateStudentInstitution(@PathVariable Long studentId, @PathVariable Long institutionId){
        StudentResponseDto studentResponseDto = studentService.updateStudentInstitution(institutionId, studentId);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    @PostMapping("/add-course/{studentId}/{courseId}")
    public ResponseEntity<StudentResponseDto> assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId){
        StudentResponseDto studentResponseDto = studentService.addStudentCourse(courseId, studentId);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }


}
