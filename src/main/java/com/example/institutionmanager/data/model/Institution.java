package com.example.institutionmanager.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                ", courses=" + courses +
                '}';
    }
}
