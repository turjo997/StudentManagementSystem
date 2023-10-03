package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.model.CourseUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    ResponseEntity<String> createCourse(CourseCreateRequest courseCreateRequest);

    ResponseEntity<APIResponse<?>> getAllCourse();

    ResponseEntity<APIResponse<?>> getAllCourseMaterial();

    ResponseEntity<APIResponse<?>> getCourseById(Long courseId);

    ResponseEntity<APIResponse<?>> updateCourse(CourseUpdateRequest courseUpdateRequest);

    ResponseEntity<String> deleteCourse(Long courseId);

}
