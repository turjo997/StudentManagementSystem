package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.model.TeacherCreateRequest;
import org.springframework.http.ResponseEntity;

public interface TeacherService {

   ResponseEntity<String> addTeacher(TeacherCreateRequest teacherCreateRequest);



}
