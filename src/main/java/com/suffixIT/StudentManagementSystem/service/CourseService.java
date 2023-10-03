package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.ApiResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    ResponseEntity<String> createCourse(CourseCreateRequest courseCreateRequest);

    ResponseEntity<ApiResponse<?>> getAllCourse();

    ResponseEntity<ApiResponse<?>> getAllCourseMaterial();
}
