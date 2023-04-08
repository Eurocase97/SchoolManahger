package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Subject> createStudent(@RequestBody Subject subject) {

        return subjectService.save(subject);
    }

    @GetMapping()
    public ResponseEntity<Page<Subject>> getAllSubject(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                        @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return subjectService.getAllSubject(page, size);
    }

    @GetMapping("{subjectId}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long subjectId){
        return subjectService.getSubject(subjectId);
    }

    @PostMapping("{subjectId}/teacher/{teacherId}")
    public ResponseEntity<Subject> addTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long teacherId ){
        return subjectService.addTeacherToSubject(subjectId, teacherId);
    }

    @GetMapping("{subjectId}/teacher")
    public ResponseEntity<Teacher> getTeacherToSubject(@PathVariable Long subjectId){
        return subjectService.getTeacherToSubject(subjectId);
    }

    @GetMapping("/{subjectId}/students")
    public ResponseEntity<Page<Student>> getStudentAssigned(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                           @RequestParam(value ="size", required = false, defaultValue ="10") int size,
                                                           @PathVariable long subjectId){
        return subjectService.getStudentAssigned(page, size, subjectId);
    }

    @PostMapping("/{subjectId}/exam")
    public ResponseEntity<Exam> createExam(@PathVariable Long subjectId ,@RequestBody Exam exam){
        return subjectService.createExam(subjectId, exam);
    }

    @GetMapping("/{subjectId}/exam")
    public ResponseEntity<Set<Exam>> getExam(@PathVariable Long subjectId){
        return subjectService.getExamAssignedToSubject(subjectId);
    }

}