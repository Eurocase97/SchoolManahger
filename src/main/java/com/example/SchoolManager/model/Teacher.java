package com.example.SchoolManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy="teacher")
    @JsonIgnore
    private Set<Subject> subjects;

    public boolean addSubject(Subject subject) {
        return subjects.add(subject);
    }
}
