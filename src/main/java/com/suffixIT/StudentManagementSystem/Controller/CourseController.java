package com.suffixIT.StudentManagementSystem.Controller;

import com.suffixIT.StudentManagementSystem.Request.CourseRequest;
import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.Service.CourseService;
import com.suffixIT.StudentManagementSystem.entity.Course;
import com.suffixIT.StudentManagementSystem.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addCourse(@Valid  @RequestBody CourseRequest courseRequest){
        MessageResponse messageResponse = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> updateCourse(@RequestBody CourseRequest courseRequest){
        MessageResponse messageResponse = courseService.updateCourse(courseRequest.getCourseId(), courseRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> courseList = courseService.getAllCourse();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/find/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Integer courseId){
        Course course = courseService.getASingleCourse(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<MessageResponse> deleteCourse(@PathVariable("courseId") Integer courseId) {
        MessageResponse messageResponse = courseService.deleteCourse(courseId);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
