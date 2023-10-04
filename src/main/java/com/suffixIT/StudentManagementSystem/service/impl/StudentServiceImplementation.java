package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.StudentEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseServiceException;
import com.suffixIT.StudentManagementSystem.exception.StudentServiceException;
import com.suffixIT.StudentManagementSystem.exception.TeacherServiceException;
import com.suffixIT.StudentManagementSystem.model.StudentCreateRequest;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.repository.StudentRepository;
import com.suffixIT.StudentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentServiceImplementation implements StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<String> addStudent(StudentCreateRequest studentCreateRequest) {
        try{

            Optional<CourseEntity> optionalCourse = courseRepository.findById(studentCreateRequest.getCourseId());

            if (optionalCourse.isPresent()) {

                String email = studentCreateRequest.getEmail();
                String firstName = studentCreateRequest.getFirstName();
                String lastName = studentCreateRequest.getLastName();
                String gender = studentCreateRequest.getGender();
                String address = studentCreateRequest.getStudentAddress();


                if(email == null || firstName == null ||  lastName == null || gender == null
                        || address == null){
                    throw new StudentServiceException("Please fill all the values");
                }

                Optional<StudentEntity> user = studentRepository.findByEmail(email);

                if(user.isPresent()){

                    optionalCourse.get().addStudents(user.get());
                    courseRepository.save(optionalCourse.get());

                }else{

                    StudentEntity studentEntity = StudentEntity.builder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .studentAddress(address)
                            .gender(gender)
                            .email(email)
                            .build();

                    optionalCourse.get().addStudents(studentEntity);

                    studentRepository.save(studentEntity);
                    courseRepository.save(optionalCourse.get());

                }

                return ResponseEntity.ok("Student enrolled successfully.");

            }else{
                throw new CourseServiceException("Course is not found");
            }

        }catch (CourseServiceException e1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }catch (StudentServiceException e2){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the student.");
        }
    }
}
