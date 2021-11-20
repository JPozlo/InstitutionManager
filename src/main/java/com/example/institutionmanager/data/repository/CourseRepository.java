package com.example.institutionmanager.data.repository;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstitutionId(Long institutionId);
    List<Course> findByName(String name);
}
