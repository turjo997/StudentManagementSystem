package com.suffixIT.StudentManagementSystem.controllers;

import com.suffixIT.StudentManagementSystem.model.StudentCreateRequest;
import com.suffixIT.StudentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class StudentController {

    private final StudentService studentService;


    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {
        return studentService.addStudent(studentCreateRequest);
    }


}
