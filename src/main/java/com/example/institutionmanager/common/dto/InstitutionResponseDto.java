package com.example.institutionmanager.common.dto;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Student;
import org.springframework.lang.Nullable;

import java.util.List;

public class InstitutionResponseDto {
    private Long id;
    private String name;

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
}
