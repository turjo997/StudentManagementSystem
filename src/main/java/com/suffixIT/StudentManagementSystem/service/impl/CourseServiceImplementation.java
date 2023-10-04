package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.CourseMaterialEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseServiceException;
import com.suffixIT.StudentManagementSystem.exception.CourseMaterialServiceException;
import com.suffixIT.StudentManagementSystem.model.APIResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.model.CourseUpdateRequest;
import com.suffixIT.StudentManagementSystem.repository.CourseMaterialRepository;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository courseMaterialRepository;


    @Override
    public ResponseEntity<String> createCourse(CourseCreateRequest courseCreateRequest) {

        try{
            String Title = courseCreateRequest.getTitle();
            Integer Credit = courseCreateRequest.getCredit();

            if(Title == null || Credit == null){
                throw new CourseServiceException("Please fill all the values");
            }else{
                CourseEntity courseEntity = CourseEntity.builder()
                        .title(courseCreateRequest.getTitle())
                        .credit(courseCreateRequest.getCredit())
                        .build();

                courseRepository.save(courseEntity);

                CourseMaterialEntity courseMaterialEntity = CourseMaterialEntity
                        .builder()
                        .courseEntity(courseEntity)
                        .url(courseCreateRequest.getUrl())
                        .build();

                courseMaterialRepository.save(courseMaterialEntity);
                return ResponseEntity.ok("Course is added");
            }

        }catch (CourseServiceException e1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the course.");
        }

    }

    @Override
    public ResponseEntity<APIResponse<?>> getAllCourse() {
        try {
            List<CourseEntity> courses = courseRepository.findAll();
            if (courses.isEmpty()) {
                throw new CourseServiceException("There is no course available right now");
            }

            List<CourseEntity> modelList = new ArrayList<>();
            courses.forEach(courseEntity -> {
                modelList.add(
                        CourseEntity.builder()
                                .courseId(courseEntity.getCourseId())
                                .credit(courseEntity.getCredit())
                                .title(courseEntity.getTitle())
                                .build()
                );
            });
            APIResponse<List<CourseEntity>> response = new APIResponse<List<CourseEntity>>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (CourseServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> getAllCourseMaterial() {
        try {
            List<CourseMaterialEntity> courseMaterials = courseMaterialRepository.findAll();
            if (courseMaterials.isEmpty()) {
                throw new CourseMaterialServiceException("There is no course material available right now");
            }

            List<CourseMaterialEntity> modelList = new ArrayList<>();
            courseMaterials.forEach(courseMaterialEntity -> {
                modelList.add(
                        CourseMaterialEntity.builder()
                                .courseMaterialId(courseMaterialEntity.getCourseMaterialId())
                                .courseEntity(courseMaterialEntity.getCourseEntity())
                                .url(courseMaterialEntity.getUrl())
                                .build()
                );
            });
            APIResponse<List<CourseMaterialEntity>> response = new APIResponse<List<CourseMaterialEntity>>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (CourseMaterialServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> getCourseById(Long courseId) {
        try {
            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
            List<CourseMaterialEntity> optionalCourseMaterial = courseMaterialRepository.findByCourseId(courseId);

            if (optionalCourse.isPresent()) {

                CourseCreateRequest courseModel = CourseCreateRequest.builder()
                        .courseId(optionalCourse.get().getCourseId())
                        .title(optionalCourse.get().getTitle())
                        .credit(optionalCourse.get().getCredit())
                        .url(String.valueOf(optionalCourseMaterial.get(0).getUrl()))
                        .build();

                APIResponse<CourseCreateRequest> apiResponse = new APIResponse<>(courseModel, null);

                // Return the ResponseEntity with the APIResponse
                return ResponseEntity.ok(apiResponse);

            } else {
                throw new CourseServiceException("course not found");
            }
        } catch (CourseServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> updateCourse(CourseUpdateRequest courseUpdateRequest) {
        try {
            Optional<CourseEntity> optionalCourse = courseRepository.findById(courseUpdateRequest.getCourseId());

            if (optionalCourse.isPresent()) {
                CourseEntity course = optionalCourse.get();
                course.setCredit(courseUpdateRequest.getCredit());
                course.setTitle(courseUpdateRequest.getTitle());

                courseRepository.save(course);

                Optional<CourseEntity> Course = courseRepository.findById(courseUpdateRequest.getCourseId());

                if(Course.isPresent()){
                    CourseUpdateRequest model = CourseUpdateRequest.builder()
                            .courseId(Course.get().getCourseId())
                            .title(Course.get().getTitle())
                            .credit(Course.get().getCredit())
                            .build();
                    APIResponse<CourseUpdateRequest> apiResponse = new APIResponse<>(model, null);

                    return ResponseEntity.ok(apiResponse);
                }else{
                    throw new CourseServiceException("Course not found");
                }

            } else {
                throw new CourseServiceException("Course not found");
            }
        }
        catch (CourseServiceException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<String> deleteCourse(Long courseId) {
        try {
            if (courseRepository.existsById(courseId)) {
                List<CourseMaterialEntity> optionalCourseMaterial = courseMaterialRepository.findByCourseId(courseId);

                if (!optionalCourseMaterial.isEmpty()) {
                    courseMaterialRepository.deleteById(optionalCourseMaterial.get(0).getCourseMaterialId());
                }

                courseRepository.deleteById(courseId);

                String message = "Course is deleted successfully";
                return ResponseEntity.ok(message);
            } else {
                throw new CourseServiceException("Course not found");
            }
        } catch (CourseServiceException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the course.");
        }
    }

}