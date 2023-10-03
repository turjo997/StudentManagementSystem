package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Request.CourseRequest;
import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Course;
import com.suffixIT.StudentManagementSystem.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseService {
    MessageResponse createCourse(CourseRequest courseRequest);
    MessageResponse updateCourse(Integer courseId, CourseRequest courseRequest);
    Course getASingleCourse(Integer courseId);
    List<Course> getAllCourse();
    MessageResponse deleteCourse(Integer courseId);
}
