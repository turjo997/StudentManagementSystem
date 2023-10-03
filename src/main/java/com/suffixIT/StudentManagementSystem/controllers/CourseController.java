package com.suffixIT.StudentManagementSystem.controllers;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.model.CourseUpdateRequest;
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
    public ResponseEntity<APIResponse<?>> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/getAllCourseMaterial")
    public ResponseEntity<APIResponse<?>> getAllCourseMaterial(){
        return courseService.getAllCourseMaterial();
    }

    @GetMapping("/get/course/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
    }


    @PutMapping("/update/course")
    public ResponseEntity<APIResponse<?>>  update(@RequestBody CourseUpdateRequest courseUpdateRequest){
        return courseService.updateCourse(courseUpdateRequest);
    }


    @DeleteMapping("/delete/course/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        return courseService.deleteCourse(courseId);
    }
}
