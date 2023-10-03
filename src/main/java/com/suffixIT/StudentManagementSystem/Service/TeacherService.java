package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Request.TeacherRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Student;
import com.suffixIT.StudentManagementSystem.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherService {
    MessageResponse createTeacher(TeacherRequest teacherRequest);
    MessageResponse updateTeacher(Integer teacherId, TeacherRequest teacherRequest);
    Teacher getASingleTeacher(Integer teacherId);
    List<Teacher> getAllTeacher();
    MessageResponse deleteTeacher(Integer teacherId);
}
