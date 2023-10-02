package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {
    MessageResponse createStudent(StudentRequest studentRequest);
    MessageResponse updateStudent(Integer studentId, StudentRequest studentRequest);
    Student getASingleStudent(Integer studentId);
    List<Student> getAllStudent();
    MessageResponse deleteStudent(Integer studentId);
}
