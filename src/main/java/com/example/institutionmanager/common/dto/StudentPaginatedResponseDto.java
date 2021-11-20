package com.example.institutionmanager.common.dto;

import com.example.institutionmanager.data.model.Course;
import org.springframework.lang.Nullable;

import java.util.List;

public class StudentPaginatedResponseDto {
    private Long id;
    private String name;
    private String admissionNumber;
    @Nullable
    private List<Course> courses;
    private int pageNo;
    private int pageSize;
    private int totalItems;

    public StudentPaginatedResponseDto(Long id, String name, String admissionNumber, int pageNo, int pageSize, int totalSize) {
        this.id = id;
        this.name = name;
        this.admissionNumber = admissionNumber;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalItems = totalSize;
    }

    public StudentPaginatedResponseDto() {
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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
