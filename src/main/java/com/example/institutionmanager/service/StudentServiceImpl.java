package com.example.institutionmanager.service;

import com.example.institutionmanager.common.dto.*;
import com.example.institutionmanager.common.exception.NameAlreadyTakenException;
import com.example.institutionmanager.common.exception.ResourceNotFoundException;
import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.data.model.Student;
import com.example.institutionmanager.data.repository.CourseRepository;
import com.example.institutionmanager.data.repository.InstitutionRepository;
import com.example.institutionmanager.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InstitutionRepository institutionRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public StudentResponseDto createStudent(Long institutionId, StudentRequestDto studentRequestDto) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(institutionId);
        List<Student> studentList = studentRepository.findAll();
        if(optionalInstitution.isPresent()){
            Institution institution = optionalInstitution.get();
            Student student = new Student();
            if(studentList.isEmpty()){
                    student.setName(studentRequestDto.getName());
                    student.setAdmissionNumber(studentRequestDto.getAdmissionNumber());
                    student.setInstitution(institution);
                    Student savedStudent = studentRepository.save(student);
                    return new StudentResponseDto(savedStudent.getId(), savedStudent.getName(), savedStudent.getAdmissionNumber(), savedStudent.getInstitution());
            } else{
                boolean admissionNumberExists = studentList.stream().anyMatch(student1 -> student1.getAdmissionNumber().equals(studentRequestDto.getAdmissionNumber()));
                if(admissionNumberExists){
                    throw new NameAlreadyTakenException("Student with the same admission number already exists");
                } else{
                    student.setName(studentRequestDto.getName());
                    student.setAdmissionNumber(studentRequestDto.getAdmissionNumber());
                    student.setInstitution(institution);
                    Student savedStudent = studentRepository.save(student);
                    return new StudentResponseDto(savedStudent.getId(), savedStudent.getName(), savedStudent.getAdmissionNumber());
                }
            }
        } else{
            throw new ResourceNotFoundException("Institution with the id of " + institutionId + " does not exist");
        }
    }

    @Override
    public StudentResponseDto updateStudentName(Long studentId, String name) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
                optionalStudent.get().setName(name);
                Student updatedStudent = studentRepository.save(optionalStudent.get());
                return new StudentResponseDto(updatedStudent.getId(), updatedStudent.getName(), updatedStudent.getAdmissionNumber());
        } else{
            throw new ResourceNotFoundException("Student with the id of " + studentId + " not found");
        }
    }

    @Override
    public ApiResponse deleteStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
            studentRepository.delete(optionalStudent.get());
            return new ApiResponse("Student successfully deleted");
        } else{
            throw new ResourceNotFoundException("Student with the id of " + studentId + " not found");
        }
    }

    @Override
    public List<StudentPaginatedResponseDto> getAllStudentsByInstitution(Long institutionId, Pageable pageable) {
        Page<Student> institutionStudents = studentRepository.findByInstitutionId(institutionId, pageable);
        if(institutionStudents.isEmpty()){
            throw new ResourceNotFoundException("There are no students available yet");
        } else{
            List<Student> studentList = institutionStudents.toList();
            List<StudentPaginatedResponseDto> studentPaginatedResponseDtoList = new ArrayList<>();
            studentList.forEach(student -> {
                StudentPaginatedResponseDto studentPaginatedResponseDto = new StudentPaginatedResponseDto(student.getId(), student.getName(), student.getAdmissionNumber(), institutionStudents.getNumber(), institutionStudents.getTotalPages(), (int) institutionStudents.getTotalElements());
                studentPaginatedResponseDtoList.add(studentPaginatedResponseDto);
            });
            return studentPaginatedResponseDtoList;
        }
    }

    @Override
    public StudentResponseDto updateStudentInstitution(Long institutionId, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
            Optional<Institution> optionalInstitution = institutionRepository.findById(institutionId);
            if(optionalInstitution.isPresent()){
                optionalStudent.get().setInstitution(optionalInstitution.get());
                Student updatedStudent = studentRepository.save(optionalStudent.get());
                return new StudentResponseDto(updatedStudent.getId(), updatedStudent.getName(), updatedStudent.getAdmissionNumber(), updatedStudent.getInstitution());
            } else{
                throw new ResourceNotFoundException("Institution with the id of " + institutionId + " not found");
            }
        } else{
            throw new ResourceNotFoundException("Student with the id of " + studentId + " not found");
        }
    }

    @Override
    public StudentResponseDto addStudentCourse(Long courseId, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
            Optional<Course> optionalCourse = courseRepository.findById(courseId);
            if(optionalCourse.isPresent()){
                optionalStudent.get().setCourses(optionalCourse.get());
                Student updatedStudent = studentRepository.save(optionalStudent.get());
                List<Course> studentCoursesList = new ArrayList<>(updatedStudent.getCourses());
                return new StudentResponseDto(updatedStudent.getId(), updatedStudent.getName(), updatedStudent.getAdmissionNumber(), studentCoursesList);
            } else{
                throw new ResourceNotFoundException("Course with the id of " + courseId + " not found");
            }
        } else{
            throw new ResourceNotFoundException("Student with the id of " + studentId + " not found");
        }
    }
}
