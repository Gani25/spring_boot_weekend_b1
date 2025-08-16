package com.sprk.student_management.repository;

import com.sprk.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // No need to write any logic for CRUD

    // Custom
    // JPQL custom query methods
    List<Student> findByGender(String gender);

    boolean existsByEmail(String email);
}
