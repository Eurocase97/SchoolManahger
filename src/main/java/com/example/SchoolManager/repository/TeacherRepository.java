package com.example.SchoolManager.repository;

import com.example.SchoolManager.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher save(Teacher teacher);
    Optional<Teacher> findByName(String name);

    @Query(value = "SELECT t FROM Teacher t", nativeQuery = false)
    Page<Teacher> getAll(Pageable paging);
}
