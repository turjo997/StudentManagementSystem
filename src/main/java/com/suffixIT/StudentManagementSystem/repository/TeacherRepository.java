package com.suffixIT.StudentManagementSystem.repository;

import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity , Long> {

    Optional<TeacherEntity> findByEmail(String email);
}
