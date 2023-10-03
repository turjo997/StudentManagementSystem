package com.suffixIT.StudentManagementSystem.Repository;

import com.suffixIT.StudentManagementSystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
