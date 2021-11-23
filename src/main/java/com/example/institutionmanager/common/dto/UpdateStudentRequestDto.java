package com.example.institutionmanager.common.dto;

public class UpdateStudentRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateStudentRequestDto() {
    }

    public UpdateStudentRequestDto(String name) {
        this.name = name;
    }
}
