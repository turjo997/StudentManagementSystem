package com.suffixIT.StudentManagementSystem.controllers;

import com.suffixIT.StudentManagementSystem.model.ApiResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/create/course")
    public ResponseEntity<String> createCourse(@RequestBody CourseCreateRequest courseCreateRequest) {
        return courseService.createCourse(courseCreateRequest);
    }

    @GetMapping("/getAllCourse")
    public ResponseEntity<ApiResponse<?>> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/getAllCourseMaterial")
    public ResponseEntity<ApiResponse<?>> getAllCourseMaterial(){
        return courseService.getAllCourseMaterial();
    }

//    @GetMapping("/get/course/{courseId}")
//    public ResponseEntity<?> getCourseById(@PathVariable Long courseId){
//        return null;
//    }
//
//    @PutMapping("/update/course")
//    public ResponseEntity<String> update(@RequestBody ){
//        return
//    }

}
