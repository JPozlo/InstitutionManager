package com.example.institutionmanager.common.dto;

import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.data.model.Student;
import org.springframework.lang.Nullable;

import java.util.List;

public class CourseResponseDto {
    private Long id;
    private String name;
    private Institution institution;
    @Nullable
    private List<Student> students;

    public CourseResponseDto(Long id, String name, Institution institution) {
        this.id = id;
        this.name = name;
        this.institution = institution;
    }

    public CourseResponseDto(Long id, String name, Institution institution, @Nullable List<Student> students) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.students = students;
    }

    public CourseResponseDto() {
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Nullable
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(@Nullable List<Student> students) {
        this.students = students;
    }
}
