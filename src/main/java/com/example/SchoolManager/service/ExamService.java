package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.repository.ExamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ExamService  {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public ResponseEntity<Exam> save(Exam exam) {
        Optional<Exam> optionalExam = examRepository.findByName(exam.getName());
        if(optionalExam.isPresent()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible to create it");
        }
        return new ResponseEntity<>(examRepository.save(exam), HttpStatus.CREATED);
    }

    public ResponseEntity<Page<Exam>> getAllSubject(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Exam> examPage= examRepository.getAll(paging);
        return new ResponseEntity<>(examPage , HttpStatus.OK);
    }
}