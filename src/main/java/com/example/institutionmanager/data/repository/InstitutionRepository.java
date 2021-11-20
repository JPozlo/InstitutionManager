package com.example.institutionmanager.data.repository;

import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    List<Institution> findByName(String name);
    @Query("FROM Institution ORDER BY name ASC")
    List<Institution> findAllSortByNameAscending();
    @Query("FROM Institution ORDER BY name DESC")
    List<Institution> findAllSortByNameDescending();
}
