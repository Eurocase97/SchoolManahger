package com.example.SchoolManager.repository;

import com.example.SchoolManager.model.Student;
import com.example.SchoolManager.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long> {
    @Query(value = "SELECT s FROM Subject s", nativeQuery = false)
    Page<Subject> getAll(Pageable paging);
}
