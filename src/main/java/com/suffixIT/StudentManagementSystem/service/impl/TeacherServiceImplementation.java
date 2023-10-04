package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseServiceException;
import com.suffixIT.StudentManagementSystem.exception.TeacherServiceException;
import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.TeacherCreateRequest;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.repository.TeacherRepository;
import com.suffixIT.StudentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TeacherServiceImplementation implements TeacherService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    @Override
    public ResponseEntity<String> addTeacher(TeacherCreateRequest teacherCreateRequest) {

        try{

            Optional<CourseEntity> optionalCourse = courseRepository.findById(teacherCreateRequest.getCourseId());

            if (optionalCourse.isPresent()) {

                String email = teacherCreateRequest.getEmail();
                String firstName = teacherCreateRequest.getFirstName();
                String lastName = teacherCreateRequest.getLastName();
                String gender = teacherCreateRequest.getGender();
                String address = teacherCreateRequest.getTeacherAddress();


                if(email == null || firstName == null ||  lastName == null || gender == null
                  || address == null){
                    throw new TeacherServiceException("Please fill all the values");
                }

                Optional<TeacherEntity> user = teacherRepository.findByEmail(email);

                if(user.isPresent()){

                    optionalCourse.get().addTeachers(user.get());
                    courseRepository.save(optionalCourse.get());

                }else{

                    TeacherEntity teacherEntity = TeacherEntity.builder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .teacherAddress(address)
                            .gender(gender)
                            .email(email)
                            .build();

                    optionalCourse.get().addTeachers(teacherEntity);

                    teacherRepository.save(teacherEntity);
                    courseRepository.save(optionalCourse.get());

                }



                return ResponseEntity.ok("Teacher added successfully.");

            }else{
                throw new CourseServiceException("Course is not found");
            }

        }catch (CourseServiceException e1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }catch (TeacherServiceException e2){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the teacher.");
        }
    }
}
