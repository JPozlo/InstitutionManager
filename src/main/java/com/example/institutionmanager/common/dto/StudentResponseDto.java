package com.example.institutionmanager.common.dto;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentResponseDto {
    private Long id;
    private String name;
    private String admissionNumber;
    @Nullable
    private List<Course> courses;
    @Nullable
    private Institution institution;

    public StudentResponseDto(Long id, String name, String admissionNumber) {
        this.id = id;
        this.name = name;
        this.admissionNumber = admissionNumber;
    }

    public StudentResponseDto(Long id, String name, String admissionNumber, @Nullable List<Course> courses) {
        this.id = id;
        this.name = name;
        this.admissionNumber = admissionNumber;
        this.courses = courses;
    }

    public StudentResponseDto(Long id, String name, String admissionNumber, @Nullable Institution institution) {
        this.id = id;
        this.name = name;
        this.admissionNumber = admissionNumber;
        this.institution = institution;
    }

    public StudentResponseDto() {
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
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

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
