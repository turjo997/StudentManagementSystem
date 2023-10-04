package com.suffixIT.StudentManagementSystem.controllers;

import com.suffixIT.StudentManagementSystem.model.*;
import com.suffixIT.StudentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @PostMapping("/add/teacher")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherCreateRequest teacherCreateRequest) {
        return teacherService.addTeacher(teacherCreateRequest);
    }

    @GetMapping("/getAllTeacher")
    public ResponseEntity<APIResponse<?>> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/get/teacher/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @PutMapping("/update/teacher")
    public ResponseEntity<APIResponse<?>> update(@RequestBody TeacherUpdateRequest teacherUpdateRequest){
        return teacherService.updateTeacher(teacherUpdateRequest);
    }


}
