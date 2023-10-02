package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Exception.ResourceNotFoundException;
import com.suffixIT.StudentManagementSystem.Repository.StudentRepository;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public MessageResponse createStudent(Student student){
        Student newStudent= new Student();

        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setStudentAddress(student.getStudentAddress());
        newStudent.setGender(student.getGender());
        studentRepository.save(newStudent);
        return new MessageResponse("Student Created successfully!");
    }
    @Override
    public MessageResponse updateStudent(Integer studentId, Student student) throws ResourceNotFoundException{
        Optional<Student> studentData = studentRepository.findById(studentId);

        if(studentData.isEmpty()){
            throw new ResourceNotFoundException("Student", "studentId", studentId);

        }
        else{
            studentData.get().setFirstName(student.getFirstName());
            studentData.get().setLastName(student.getLastName());
            studentData.get().setStudentAddress(student.getStudentAddress());
            studentData.get().setGender(student.getGender());
            studentRepository.save(studentData.get());
            return new MessageResponse("Student updated successfully!");
        }

    }
    @Override
    public Student getASingleStudent(Integer studentId) throws ResourceNotFoundException{
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

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
