package com.example.SchoolManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String identityCardNumber ;

    /// @ElementCollection(targetClass= Subject.class)

    @ManyToMany(mappedBy="students", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Subject> subjects;

    @ManyToMany(mappedBy="students")
    @JsonIgnore
    private Set<Exam> exams;
    public boolean addSubject(Subject subject){
        return subjects.add(subject);
    }
}