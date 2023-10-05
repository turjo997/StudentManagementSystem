package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Exception.ResourceNotFoundException;
import com.suffixIT.StudentManagementSystem.Repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.Repository.StudentRepository;
import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Course;
import com.suffixIT.StudentManagementSystem.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public MessageResponse createStudent(StudentRequest studentRequest) {

        List<Course> courseList = courseRepository.findAllById(studentRequest.getCourseIds());

        Student newStudent = new Student();
        newStudent.setFirstName(studentRequest.getFirstName());
        newStudent.setLastName(studentRequest.getLastName());
        newStudent.setAddress(studentRequest.getAddress());
        newStudent.setGender(studentRequest.getGender());

        if (courseList.size() > 0){
            newStudent.setCourses(new HashSet<>(courseList));
        }
        try{
            studentRepository.save(newStudent);
        }
        catch(Exception e){
            return new MessageResponse("Student created failed!");
        }
        return new MessageResponse("Student Created successfully!");

    }
    @Override
    public MessageResponse updateStudent(Integer studentId, StudentRequest studentRequest) throws ResourceNotFoundException{
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));
        
            student.setFirstName(studentRequest.getFirstName());
            student.setLastName(studentRequest.getLastName());
            student.setAddress(studentRequest.getAddress());
            student.setGender(studentRequest.getGender());

            List<Course> courseList = courseRepository.findAllById(studentRequest.getCourseIds());
            if (courseList.size() > 0){
                student.setCourses(new HashSet<>(courseList));
            }

            try{
                studentRepository.save(student);
            }
            catch(Exception e){
                return new MessageResponse("Student updated failed!");
            }
            return new MessageResponse("Student updated successfully!");

    }
    @Override
    public Student getASingleStudent(Integer studentId) throws ResourceNotFoundException{
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));

    }
    @Override
    public List<Student> getAllStudent(){

        return studentRepository.findAll();
    }

    @Override
    public MessageResponse deleteStudent(Integer studentId) throws ResourceNotFoundException{
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));

        studentRepository.deleteById(student.getStudentId());
        return new MessageResponse("Student id deleted successfully!");
    }

}
