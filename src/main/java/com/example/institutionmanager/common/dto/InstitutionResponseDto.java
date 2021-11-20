package com.example.institutionmanager.common.dto;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Student;
import org.springframework.lang.Nullable;

import java.util.List;

public class InstitutionResponseDto {
    private Long id;
    private String name;
    @Nullable
    private List<Course> courses;
    @Nullable
    private List<Student> students;

    public InstitutionResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public InstitutionResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
