package com.example.institutionmanager.data.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "institution_id", unique = true)
    private Long id;
    @Column(name = "institution_name", unique = true)
    private String name;
    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;
    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses;

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

    public Institution(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Institution() {
    }
}
