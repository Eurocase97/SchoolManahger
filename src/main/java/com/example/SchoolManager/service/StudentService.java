package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return new ResponseEntity<>(studentFound ,HttpStatus.FOUND);
    }

    public ResponseEntity<List<Subject>> getSubjectEnrolled(Long student) {
       List<Subject> subjectList= studentRepository.getSubjectEnrolled(student);
        return new ResponseEntity<>(subjectList ,HttpStatus.FOUND);
    }
}