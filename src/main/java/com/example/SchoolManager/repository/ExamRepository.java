package com.example.SchoolManager.repository;

import com.example.SchoolManager.model.Exam;
import com.example.SchoolManager.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findByName(String name);

    @Query(value = "SELECT e FROM Exam e", nativeQuery = false)
    Page<Exam> getAll(Pageable paging);
}
