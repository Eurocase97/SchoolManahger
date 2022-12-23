package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student save(Student student) throws Exception {
      return studentRepository.save(student);
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student studentFound= student.get();
        return new ResponseEntity<>(studentFound ,HttpStatus.OK);
    }

    public ResponseEntity<Page<Subject>> getSubjectEnrolled(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
       Page<Subject> subjectList= studentRepository.getSubjectEnrolled(id,paging);
        return new ResponseEntity<>(subjectList ,HttpStatus.OK);
    }

    public ResponseEntity<Page<Exam>> getAllExams(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Exam> subjectList= studentRepository.getSubjectExams(id, paging);
        return new ResponseEntity<>(subjectList ,HttpStatus.OK);
    }

    public ResponseEntity<Page<Student>> getAllStudents(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Student> studentList= studentRepository.getAll(paging);
        return new ResponseEntity<>(studentList ,HttpStatus.OK);
    }
}