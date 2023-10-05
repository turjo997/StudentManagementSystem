package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.StudentCreateRequest;
import com.suffixIT.StudentManagementSystem.model.StudentUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<String> addStudent(StudentCreateRequest studentCreateRequest);
    ResponseEntity<?> getStudentById(Long studentId);

    ResponseEntity<APIResponse<?>> getAllStudent();

    ResponseEntity<APIResponse<?>> updateStudent(StudentUpdateRequest studentUpdateRequest);

    ResponseEntity<String> deleteStudent(Long studentId);

}
