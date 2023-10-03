package com.suffixIT.StudentManagementSystem.exception;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CourseEntityException.class})
    public ResponseEntity<APIResponse> CourseEntityExceptionHandler(Exception ex) {
        APIResponse<CourseEntityException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CourseMaterialEntityException.class})
    public ResponseEntity<APIResponse> CourseMaterialEntityExceptionHandler(Exception ex) {
        APIResponse<CourseMaterialEntityException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
