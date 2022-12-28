package com.example.SchoolManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String identityCardNumber ;

    @ManyToMany(mappedBy="students", fetch = FetchType.LAZY)
    @JsonIgnore
    @ElementCollection(targetClass= Subject.class)
    private Set<Subject> subjects;

    @ManyToMany(mappedBy="students")
    @JsonIgnore
    private Set<Exam> exams;
    public boolean addSubject(Subject subject){
        return subjects.add(subject);
    }
}