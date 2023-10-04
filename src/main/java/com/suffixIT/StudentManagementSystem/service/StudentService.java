package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.StudentCreateRequest;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<String> addStudent(StudentCreateRequest studentCreateRequest);
}
