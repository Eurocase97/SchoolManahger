package com.example.SchoolManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Subjects_Students",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonIgnore
    private Set<Student> students;

    @OneToMany(mappedBy="subject")
    @JsonIgnore
    private Set<Exam> exams;

    @ManyToOne()
    @JoinColumn(name="teacher_id")
    @JsonIgnore
    private Teacher teacher;
    public boolean addStudent(Student student) {
        return students.add(student);
    }
}
