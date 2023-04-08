package com.example.SchoolManager.service;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import com.example.SchoolManager.repository.StudentRepository;
import com.example.SchoolManager.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository1) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository1;
    }
    public Student save(Student student) throws Exception {
      Optional<Student> optionalStudent= studentRepository.findByIdentityCardNumber(student.getIdentityCardNumber());
      if(optionalStudent.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "impossible to create it");
      }

      if(student.getIdentityCardNumber() == null){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "impossible to create it");
      }
        return studentRepository.save(student);
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Student no exist");
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

    public ResponseEntity<Student> addSubject(Long studentId, Long subjectId) {

        Optional<Student> studentOptional= studentRepository.findById(studentId);
        Optional<Subject> subject=  subjectRepository.findById(subjectId);

        if(studentOptional.isEmpty() || subject.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "impossible to create it");
        }

        Student student = studentOptional.get();
        if (!student.addSubject(subject.get()) || !subject.get().addStudent(student)){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student already assigned ");
        }

        return new ResponseEntity<>(studentRepository.save(student) ,HttpStatus.OK) ;
    }

    public ResponseEntity<Set<Exam>> getExamAssignedToStudent(Long studentId) {
        Set<Exam> examAssigned = new HashSet<>();
        Optional<Student> studentOptional= studentRepository.findById(studentId);

        if(studentOptional.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "student not found");
        }
        /*
        for(Subject s: studentOptional.get().getSubjects()){
            examAssigned.addAll(s.getExams());
        }*/

        examAssigned = studentOptional.get().getSubjects().stream().flatMap(subject -> subject.getExams().stream()).collect(Collectors.toSet());
        return new ResponseEntity<>(examAssigned ,HttpStatus.OK) ;
    }
}