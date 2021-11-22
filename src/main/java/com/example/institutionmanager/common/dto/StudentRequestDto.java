package com.example.institutionmanager.common.dto;

import com.sun.istack.NotNull;


public class StudentRequestDto {
    private String name;
    private String admissionNumber;

    public StudentRequestDto() {
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
}
