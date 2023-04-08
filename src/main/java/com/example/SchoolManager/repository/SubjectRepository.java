package com.example.SchoolManager.repository;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long> {

    Subject save(Subject student);

    @Query(value = "SELECT s FROM Subject s", nativeQuery = false)
    Page<Subject> getAll(Pageable paging);

    @Query(value = "SELECT students FROM Subject s where s.id = :id", nativeQuery = false)
    Page<Student> getAllStudents(Pageable paging, @Param("id") Long subject);

    Optional<Subject> findByName(String name);
}
