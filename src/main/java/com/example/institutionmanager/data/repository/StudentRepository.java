package com.example.institutionmanager.data.repository;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.data.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByInstitutionId(Long institutionId, Pageable pageable);
}
