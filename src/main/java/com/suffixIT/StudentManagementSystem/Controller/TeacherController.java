package com.suffixIT.StudentManagementSystem.Controller;

import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Request.TeacherRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.Service.TeacherService;
import com.suffixIT.StudentManagementSystem.entity.Student;
import com.suffixIT.StudentManagementSystem.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTeacher(@RequestBody TeacherRequest teacherRequest){
        MessageResponse messageResponse = teacherService.createTeacher(teacherRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{teacherId}")
    public ResponseEntity<MessageResponse> updateTeacher(@PathVariable("teacherId") Integer teacherId, TeacherRequest teacherRequest){
        MessageResponse messageResponse = teacherService.updateTeacher(teacherId, teacherRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        List<Teacher> teacherList = teacherService.getAllTeacher();
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/find/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("studentId") Integer teacherId){
        Teacher teacher = teacherService.getASingleTeacher(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<MessageResponse> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        MessageResponse messageResponse=teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
}
