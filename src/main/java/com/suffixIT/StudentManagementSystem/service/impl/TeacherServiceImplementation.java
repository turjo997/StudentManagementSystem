package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.CourseMaterialEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseServiceException;
import com.suffixIT.StudentManagementSystem.exception.TeacherServiceException;
import com.suffixIT.StudentManagementSystem.model.*;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.repository.TeacherRepository;
import com.suffixIT.StudentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResponseEntity<APIResponse<?>> updateTeacher(TeacherUpdateRequest teacherUpdateRequest) {
        try {
            Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherUpdateRequest.getTeacherId());

            if (optionalTeacher.isPresent()) {
                TeacherEntity teacher = optionalTeacher.get();
                teacher.setFirstName(teacherUpdateRequest.getFirstName());
                teacher.setLastName(teacherUpdateRequest.getLastName());
                teacher.setGender(teacherUpdateRequest.getGender());
                teacher.setTeacherAddress(teacherUpdateRequest.getTeacherAddress());

                teacherRepository.save(teacher);

                Optional<TeacherEntity> Teacher = teacherRepository.findById(teacherUpdateRequest.getTeacherId());

                if(Teacher.isPresent()){
                    TeacherUpdateRequest model = TeacherUpdateRequest.builder()
                            .teacherId(Teacher.get().getTeacherId())
                            .firstName(Teacher.get().getFirstName())
                            .lastName(Teacher.get().getLastName())
                            .gender(Teacher.get().getGender())
                            .teacherAddress(Teacher.get().getTeacherAddress())
                            .build();
                    APIResponse<TeacherUpdateRequest> apiResponse = new APIResponse<>(model, null);

                    return ResponseEntity.ok(apiResponse);
                }else{
                    throw new TeacherServiceException("Teacher not found");
                }
            } else {
                throw new TeacherServiceException("Teacher not found");
            }
        }
        catch (TeacherServiceException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getTeacherById(Long teacherId) {
        try {
            Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(teacherId);

            if(optionalTeacher.isPresent()){

                List<CourseEntity>courses = new ArrayList<>();

                // Loop through the courses associated with the teacher
                optionalTeacher.get().getCourses().forEach(course -> {
                    // Add course information to the list
                    courses.add(CourseEntity.builder()
                            .courseId(course.getCourseId())
                            .title(course.getTitle())
                            .credit(course.getCredit())
                            .build());
                });


                TeacherResponseModel TeacherModel = TeacherResponseModel.builder()
                        .teacherId(optionalTeacher.get().getTeacherId())
                        .firstName(optionalTeacher.get().getFirstName())
                        .lastName(optionalTeacher.get().getLastName())
                        .gender(optionalTeacher.get().getGender())
                        .email(optionalTeacher.get().getEmail())
                        .teacherAddress(optionalTeacher.get().getTeacherAddress())
                        .courses(courses)
                        .build();

                APIResponse<TeacherResponseModel> apiResponse = new APIResponse<>(TeacherModel, null);

                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(apiResponse);
            }else{
                throw new TeacherServiceException("Teacher is not found");
            }
        } catch (TeacherServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> getAllTeacher() {
        try {
            List<TeacherEntity> teachers = teacherRepository.findAll();
            if (teachers.isEmpty()) {
                throw new TeacherServiceException("There are no teachers assigned right now");
            }

            List<TeacherResponseModel> modelList = new ArrayList<>();
            teachers.forEach(teacherEntity -> {
                // Initialize a list to store courses taught by the teacher
                List<CourseEntity> courses = new ArrayList<>();

                // Loop through the courses associated with the teacher
                teacherEntity.getCourses().forEach(course -> {
                    // Add course information to the list
                    courses.add(CourseEntity.builder()
                            .courseId(course.getCourseId())
                            .title(course.getTitle())
                            .credit(course.getCredit())
                            .build());
                });

                // Create a TeacherResponseModel with teacher and course information
                TeacherResponseModel responseModel = TeacherResponseModel.builder()
                        .teacherId(teacherEntity.getTeacherId())
                        .email(teacherEntity.getEmail())
                        .gender(teacherEntity.getGender())
                        .firstName(teacherEntity.getFirstName())
                        .lastName(teacherEntity.getLastName())
                        .teacherAddress(teacherEntity.getTeacherAddress())
                        .courses(courses)
                        .build();

                modelList.add(responseModel);
            });

            APIResponse<List<TeacherResponseModel>> response = new APIResponse<>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (TeacherServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<String> deleteTeacher(Long teacherId) {
        try {
            Optional<TeacherEntity> teacher = teacherRepository.findById(teacherId);

            if (teacher.isPresent()) {
                // Remove the teacher from associated courses
                teacher.get().getCourses()
                        .forEach(course ->
                                course.getTeachers()
                                        .remove(teacher.get()));

                // Save the courses to update the changes
                courseRepository.saveAll(teacher.get().getCourses());

                // Delete the teacher entity
                teacherRepository.deleteById(teacherId);

                String message = "Teacher deleted successfully";

                return ResponseEntity.ok(message);
            } else {
                throw new TeacherServiceException("Teacher not found");
            }
        } catch (TeacherServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the teacher.");
        }
    }
}
