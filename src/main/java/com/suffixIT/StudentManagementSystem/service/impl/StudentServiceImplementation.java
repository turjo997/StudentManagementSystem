package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.StudentEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseServiceException;
import com.suffixIT.StudentManagementSystem.exception.StudentServiceException;
import com.suffixIT.StudentManagementSystem.exception.TeacherServiceException;
import com.suffixIT.StudentManagementSystem.model.*;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.repository.StudentRepository;
import com.suffixIT.StudentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResponseEntity<?> getStudentById(Long studentId) {
        try {
            Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);

            if(optionalStudent.isPresent()){

                List<CourseEntity>courses = new ArrayList<>();

                // Loop through the courses associated with the teacher
                optionalStudent.get().getCourses().forEach(course -> {
                    // Add course information to the list
                    courses.add(CourseEntity.builder()
                            .courseId(course.getCourseId())
                            .title(course.getTitle())
                            .credit(course.getCredit())
                            .build());
                });


                StudentResponseModel StudentModel = StudentResponseModel.builder()
                        .studentId(optionalStudent.get().getStudentId())
                        .firstName(optionalStudent.get().getFirstName())
                        .lastName(optionalStudent.get().getLastName())
                        .gender(optionalStudent.get().getGender())
                        .email(optionalStudent.get().getEmail())
                        .studentAddress(optionalStudent.get().getStudentAddress())
                        .courses(courses)
                        .build();

                APIResponse<StudentResponseModel> apiResponse = new APIResponse<>(StudentModel, null);

                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(apiResponse);
            }else{
                throw new StudentServiceException("Student is not found");
            }
        } catch (StudentServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> getAllStudent() {
        try {
            List<StudentEntity> students = studentRepository.findAll();
            if (students.isEmpty()) {
                throw new StudentServiceException("There are no students enrolled right now");
            }

            List<StudentResponseModel> modelList = new ArrayList<>();
            students.forEach(studentEntity -> {
                // Initialize a list to store courses taught by the teacher
                List<CourseEntity> courses = new ArrayList<>();

                // Loop through the courses associated with the teacher
                studentEntity.getCourses().forEach(course -> {
                    // Add course information to the list
                    courses.add(CourseEntity.builder()
                            .courseId(course.getCourseId())
                            .title(course.getTitle())
                            .credit(course.getCredit())
                            .build());
                });

                // Create a TeacherResponseModel with teacher and course information
                StudentResponseModel responseModel = StudentResponseModel.builder()
                        .studentId(studentEntity.getStudentId())
                        .email(studentEntity.getEmail())
                        .gender(studentEntity.getGender())
                        .firstName(studentEntity.getFirstName())
                        .lastName(studentEntity.getLastName())
                        .studentAddress(studentEntity.getStudentAddress())
                        .courses(courses)
                        .build();

                modelList.add(responseModel);
            });

            APIResponse<List<StudentResponseModel>> response = new APIResponse<>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (StudentServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> updateStudent(StudentUpdateRequest studentUpdateRequest) {
        try {
            Optional<StudentEntity> optionalStudent = studentRepository.findById(studentUpdateRequest.getStudentId());

            if (optionalStudent.isPresent()) {
                StudentEntity student  = optionalStudent.get();
                student.setFirstName(studentUpdateRequest.getFirstName());
                student.setLastName(studentUpdateRequest.getLastName());
                student.setGender(studentUpdateRequest.getGender());
                student.setStudentAddress(studentUpdateRequest.getStudentAddress());

                studentRepository.save(student);

                Optional<StudentEntity> Student = studentRepository.findById(studentUpdateRequest.getStudentId());

                if(Student.isPresent()){
                    StudentUpdateRequest model = StudentUpdateRequest.builder()
                            .studentId(Student.get().getStudentId())
                            .firstName(Student.get().getFirstName())
                            .lastName(Student.get().getLastName())
                            .gender(Student.get().getGender())
                            .studentAddress(Student.get().getStudentAddress())
                            .build();
                    APIResponse<StudentUpdateRequest> apiResponse = new APIResponse<>(model, null);

                    return ResponseEntity.ok(apiResponse);
                }else{
                    throw new StudentServiceException("Student not found");
                }
            } else {
                throw new StudentServiceException("Student not found");
            }
        }
        catch (StudentServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<String> deleteStudent(Long studentId) {
        try {
            Optional<StudentEntity> student = studentRepository.findById(studentId);

            if (student.isPresent()) {
                // Remove the teacher from associated courses
                student.get().getCourses()
                        .forEach(course ->
                                course.getStudents()
                                        .remove(student.get()));

                // Save the courses to update the changes
                courseRepository.saveAll(student.get().getCourses());


                studentRepository.deleteById(studentId);

                String message = "Student deleted successfully";

                return ResponseEntity.ok(message);
            } else {
                throw new StudentServiceException("Student not found");
            }
        } catch (StudentServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the student.");
        }
    }
}
