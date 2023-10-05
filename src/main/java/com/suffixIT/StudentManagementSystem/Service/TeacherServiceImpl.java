package com.suffixIT.StudentManagementSystem.Service;

import com.suffixIT.StudentManagementSystem.Exception.ResourceNotFoundException;
import com.suffixIT.StudentManagementSystem.Repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.Repository.TeacherRepository;
import com.suffixIT.StudentManagementSystem.Request.StudentRequest;
import com.suffixIT.StudentManagementSystem.Request.TeacherRequest;
import com.suffixIT.StudentManagementSystem.Response.MessageResponse;
import com.suffixIT.StudentManagementSystem.entity.Course;
import com.suffixIT.StudentManagementSystem.entity.Student;
import com.suffixIT.StudentManagementSystem.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public MessageResponse createTeacher(TeacherRequest teacherRequest) {

        List<Course> courseList = courseRepository.findAllById(teacherRequest.getCourseIds());

        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(teacherRequest.getFirstName());
        newTeacher.setLastName(teacherRequest.getLastName());
        newTeacher.setAddress(teacherRequest.getAddress());
        newTeacher.setGender(teacherRequest.getGender());

        if (courseList.size() > 0){
                newTeacher.setCourses((Set<Course>) new HashSet<>(courseList));
        }
        try{
            teacherRepository.save(newTeacher);
        }
        catch(Exception e){
            return new MessageResponse("Student created failed!");
        }
        return new MessageResponse("Student Created successfully!");
    }

    @Override
    public MessageResponse updateTeacher(Integer teacherId, TeacherRequest teacherRequest) throws ResourceNotFoundException {
        Optional<Teacher> teacherData = teacherRepository.findById(teacherId);



        if(teacherData.isEmpty()){
            throw new ResourceNotFoundException("Teacher", "teacherId", teacherId);

        }
        else{
            teacherData.get().setFirstName(teacherRequest.getFirstName());
            teacherData.get().setLastName(teacherRequest.getLastName());
            teacherData.get().setAddress(teacherRequest.getAddress());
            teacherData.get().setGender(teacherRequest.getGender());
            //teacherData.get().setCourses(Set<Course>  );
            try{
                teacherRepository.save(teacherData.get());
            }
            catch(NullPointerException e){
                return new MessageResponse("Teacher updated failed!");
            }
            return new MessageResponse("Teacher updated successfully!");
        }

    }

    @Override
    public Teacher getASingleTeacher(Integer teacherId) throws ResourceNotFoundException{
        return teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "teacherId", teacherId));

    }

    @Override
    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    @Override
    public MessageResponse deleteTeacher(Integer teacherId) throws ResourceNotFoundException{
        final Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", teacherId));

        teacherRepository.deleteById(teacher.getTeacherId());
        return new MessageResponse("Teacher id deleted successfully!");
    }
}
