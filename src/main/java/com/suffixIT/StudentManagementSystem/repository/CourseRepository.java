package com.suffixIT.StudentManagementSystem.repository;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
