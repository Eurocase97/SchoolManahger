package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.service.ExamService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

   /* @PostMapping
    public ResponseEntity<Exam> createTeacher(@RequestBody Exam exam) {
        return examService.save(exam);
    }*/

    @GetMapping()
    public ResponseEntity<Page<Exam>> getAllTeacher(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                       @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return examService.getAllSubject(page, size);
    }
}
