package com.example.SchoolManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "Subjects_Students",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonIgnore
    private Set<Student> students;

    @OneToMany(mappedBy="subject")
    @JsonIgnore
    private Set<Exam> exams;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @JsonIgnore
    private Teacher teacher;

}
