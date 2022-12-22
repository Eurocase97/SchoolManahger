package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) throws Exception {
        return studentService.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/{id}/subject")
    public ResponseEntity<List<Subject>> getSubjectEnrolled(@PathVariable Long id){
        return studentService.getSubjectEnrolled(id);
    }
}