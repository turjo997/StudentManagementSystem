package com.suffixIT.StudentManagementSystem.repository;

import com.suffixIT.StudentManagementSystem.entity.StudentEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByEmail(String email);
}
