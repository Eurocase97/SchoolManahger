package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.model.Teacher;
import com.example.SchoolManager.repository.SubjectRepository;
import com.example.SchoolManager.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    private final TeacherRepository teacherRepository;

    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<Subject> save(Subject subject) {
        Optional<Subject> subjectOptional= subjectRepository.findByName(subject.getName());
        if(subjectOptional.isPresent()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible to create it");
        }
        return new ResponseEntity<>(subjectRepository.save(subject), HttpStatus.CREATED);
    }

    public ResponseEntity<Page<Subject>> getAllSubject(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Subject> studentList= subjectRepository.getAll(paging);
        return new ResponseEntity<>(studentList , HttpStatus.OK);
    }

    public ResponseEntity<Subject> addTeacherToSubject(Long subjectId, Long teacherId) {
       Optional<Subject> subject= subjectRepository.findById(subjectId);
       Optional<Teacher> teacher= teacherRepository.findById(teacherId);

       if(subject.isEmpty() || teacher.isEmpty()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong with the id sent");
       }

       if(subject.get().getTeacher() == null){
           subject.get().setTeacher(teacher.get());
           subjectRepository.save(subject.get());
           teacherRepository.save(teacher.get());
           return new ResponseEntity<>(subject.get() , HttpStatus.OK);
       }else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This subject already have a teacher");
       }
    }

    public ResponseEntity<Teacher> getTeacherToSubject(Long subjectId) {
        Optional<Subject> subject= subjectRepository.findById(subjectId);
        if(subject.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong with the id sent");
        }

        if(subject.get().getTeacher() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not teacher assigned yet");
        }

        return new ResponseEntity<>(subject.get().getTeacher() , HttpStatus.OK);
    }
}
