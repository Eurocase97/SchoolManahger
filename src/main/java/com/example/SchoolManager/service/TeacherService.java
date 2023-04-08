package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.repository.ExamRepository;
import com.example.SchoolManager.repository.SubjectRepository;
import com.example.SchoolManager.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;

    public TeacherService(TeacherRepository teacherRepository, ExamRepository examRepository,
                          SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
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

        }

        return new ResponseEntity<>(teacher.get().getSubjects(), HttpStatus.OK);
    }

    public ResponseEntity<Exam> createExam(Long id, Long idSubject, LocalDateTime date, String examName) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something gone wrong with teacher id");
        }
        Optional<Subject> subject = subjectRepository.findById(idSubject);
        if(subject.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something gone wrong with subject id");
        }
        if(subject.get().getTeacher().equals(teacher.get())){
            Exam exam = new Exam();
            exam.setDate(date);
            exam.setName(examName);
            subject.get().addExam(exam);
            return new ResponseEntity<>(exam, HttpStatus.OK);

        }else {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "professor and subject not match");
        }
    }
}