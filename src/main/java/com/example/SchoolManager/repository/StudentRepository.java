package com.example.SchoolManager.repository;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    Optional<Student> findById(Long aLong);

    Student save(Student student);

    @Query(value = "SELECT s.subjects FROM Student s where s.id = :id", nativeQuery = false)
    Page<Subject> getSubjectEnrolled (@Param("id") Long student, Pageable pageable);

    @Query(value = "SELECT s.exams FROM Student s where s.id = :id", nativeQuery = false)
    Page<Exam> getSubjectExams (@Param("id") Long student, Pageable pageable);

    @Query(value = "SELECT s FROM Student s", nativeQuery = false)
    Page<Student> getAll(Pageable pageable);
}
