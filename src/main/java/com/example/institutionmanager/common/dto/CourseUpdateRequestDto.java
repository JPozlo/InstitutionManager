package com.example.institutionmanager.common.dto;

public class CourseUpdateRequestDto {
    private String name;
    private Long institutionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public CourseUpdateRequestDto(String name, Long institutionId) {
        this.name = name;
        this.institutionId = institutionId;
    }
}
