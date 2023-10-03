package com.suffixIT.StudentManagementSystem.Request;

import com.suffixIT.StudentManagementSystem.entity.Student;
import com.suffixIT.StudentManagementSystem.entity.Teacher;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public class CourseRequest {
    private String title;
    private Integer credit;
    @ManyToMany
    @JoinColumn(name="student_id")
    private ArrayList<Student> students;

    @ManyToMany
    @JoinColumn(name="teacher_id")
    private ArrayList<Teacher> teachers;
}
