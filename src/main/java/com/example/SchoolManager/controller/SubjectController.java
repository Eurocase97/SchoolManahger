package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject createStudent(@RequestBody Subject subject) {

        return subjectService.save(subject);
    }

    @GetMapping()
    public ResponseEntity<Page<Subject>> getAllSubject(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                        @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return subjectService.getAllSubject(page, size);
    }
    
}
