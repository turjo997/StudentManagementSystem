package com.suffixIT.StudentManagementSystem.exception;

import com.suffixIT.StudentManagementSystem.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CourseEntityException.class})
    public ResponseEntity<ApiResponse> CourseEntityExceptionHandler(Exception ex) {
        ApiResponse<CourseEntityException> apiResponse = new ApiResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CourseMaterialEntityException.class})
    public ResponseEntity<ApiResponse> CourseMaterialEntityExceptionHandler(Exception ex) {
        ApiResponse<CourseMaterialEntityException> apiResponse = new ApiResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
