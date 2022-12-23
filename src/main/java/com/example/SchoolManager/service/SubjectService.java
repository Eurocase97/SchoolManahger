package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public ResponseEntity<Page<Subject>> getAllSubject(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Subject> studentList= subjectRepository.getAll(paging);
        return new ResponseEntity<>(studentList , HttpStatus.OK);
    }

}
