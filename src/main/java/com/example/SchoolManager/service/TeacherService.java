package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<Teacher> save(Teacher teacher) {
        Optional<Teacher>  teacherOptional = teacherRepository.findByName(teacher.getName());
        if(teacherOptional.isPresent()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible to create it");
        }
        return new ResponseEntity<>(teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    public ResponseEntity<Page<Teacher>> getAllTeacher(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Teacher> teacherPage= teacherRepository.getAll(paging);
        return new ResponseEntity<>(teacherPage , HttpStatus.OK);
    }

    public ResponseEntity<Set<Subject>> getAllSubjectAssignedToTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not correct");
        }

        if(teacher.get().getSubjects().isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Has not subject assigned");
        }

        return new ResponseEntity<>(teacher.get().getSubjects() , HttpStatus.OK);
    }
}
