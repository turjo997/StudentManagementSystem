package com.suffixIT.StudentManagementSystem.service;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.TeacherCreateRequest;
import com.suffixIT.StudentManagementSystem.model.TeacherUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface TeacherService {

   ResponseEntity<String> addTeacher(TeacherCreateRequest teacherCreateRequest);


   ResponseEntity<APIResponse<?>> updateTeacher(TeacherUpdateRequest teacherUpdateRequest);

   ResponseEntity<?> getTeacherById(Long teacherId);

   ResponseEntity<APIResponse<?>> getAllTeacher();

   ResponseEntity<String> deleteTeacher(Long teacherId);
}
