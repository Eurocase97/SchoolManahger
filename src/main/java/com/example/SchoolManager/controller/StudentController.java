package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping()
    public ResponseEntity<Page<Student>> getAllStudents(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                        @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return studentService.getAllStudents(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/{id}/subject")
    public ResponseEntity<Page<Subject>> getSubjectEnrolled(@PathVariable Long id,
                                                            @RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                            @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return studentService.getSubjectEnrolled(id, page, size);
    }

    @GetMapping("/{id}/exam")
    public ResponseEntity<Page<Exam>> getExams(@PathVariable Long id,
                                               @RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                               @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return studentService.getAllExams(id, page, size);
    }

    @PostMapping("/{studentId}/subject/{subjectId}")
    public ResponseEntity<Student> addSubject(@PathVariable Long studentId, @PathVariable Long subjectId ){
        return studentService.addSubject(studentId,subjectId);
    }

    @GetMapping("/{studentId}/GetAllExam")
    public ResponseEntity<Set<Exam>> getAllExam(@PathVariable Long studentId){
        return studentService.getExamAssignedToStudent(studentId);
     }
}