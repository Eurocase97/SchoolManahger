package com.example.SchoolManager.controller;

import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    @GetMapping()
    public ResponseEntity<Page<Teacher>> getAllTeacher(@RequestParam(value = "page", required = false, defaultValue ="0") int page,
                                                       @RequestParam(value ="size", required = false, defaultValue ="10") int size){
        return teacherService.getAllTeacher(page, size);
    }

    @GetMapping("/{id}/subject")
    public ResponseEntity<Set<Subject>> getSubjectAssigned(@PathVariable Long id){
        return teacherService.getAllSubjectAssignedToTeacher(id);
    }
}
