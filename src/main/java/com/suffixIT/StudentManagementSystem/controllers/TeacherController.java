package com.suffixIT.StudentManagementSystem.controllers;

import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.model.TeacherCreateRequest;
import com.suffixIT.StudentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @PostMapping("/add/teacher")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherCreateRequest teacherCreateRequest) {
        return teacherService.addTeacher(teacherCreateRequest);
    }


}
