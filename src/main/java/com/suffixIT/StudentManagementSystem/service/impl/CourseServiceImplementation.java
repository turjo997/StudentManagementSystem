package com.suffixIT.StudentManagementSystem.service.impl;

import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.CourseMaterialEntity;
import com.suffixIT.StudentManagementSystem.exception.CourseEntityException;
import com.suffixIT.StudentManagementSystem.exception.CourseMaterialEntityException;
import com.suffixIT.StudentManagementSystem.model.ApiResponse;
import com.suffixIT.StudentManagementSystem.model.CourseCreateRequest;
import com.suffixIT.StudentManagementSystem.repository.CourseMaterialRepository;
import com.suffixIT.StudentManagementSystem.repository.CourseRepository;
import com.suffixIT.StudentManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository courseMaterialRepository;


    @Override
    public ResponseEntity<String> createCourse(CourseCreateRequest courseCreateRequest) {

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

    @Override
    public ResponseEntity<ApiResponse<?>> getAllCourse() {
        try {
            List<CourseEntity> courses = courseRepository.findAll();
            if (courses.isEmpty()) {
                throw new CourseEntityException("There is no course available right now");
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
            ApiResponse<List<CourseEntity>> response = new ApiResponse<List<CourseEntity>>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (CourseEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllCourseMaterial() {
        try {
            List<CourseMaterialEntity> courseMaterials = courseMaterialRepository.findAll();
            if (courseMaterials.isEmpty()) {
                throw new CourseMaterialEntityException("There is no course material available right now");
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
            ApiResponse<List<CourseMaterialEntity>> response = new ApiResponse<List<CourseMaterialEntity>>(modelList, null);
            return ResponseEntity.ok(response);
        } catch (CourseMaterialEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(e.getMessage(), null));
        }
    }




}
