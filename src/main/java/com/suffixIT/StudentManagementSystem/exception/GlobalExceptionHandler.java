package com.suffixIT.StudentManagementSystem.exception;

import com.suffixIT.StudentManagementSystem.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CourseServiceException.class})
    public ResponseEntity<APIResponse> CourseServiceExceptionHandler(Exception ex) {
        APIResponse<CourseServiceException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CourseMaterialServiceException.class})
    public ResponseEntity<APIResponse> CourseMaterialServiceExceptionHandler(Exception ex) {
        APIResponse<CourseMaterialServiceException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TeacherServiceException.class})
    public ResponseEntity<APIResponse> TeacherServiceExceptionHandler(Exception ex) {
        APIResponse<TeacherServiceException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({StudentServiceException.class})
    public ResponseEntity<APIResponse> StudentServiceExceptionHandler(Exception ex) {
        APIResponse<StudentServiceException> apiResponse = new APIResponse<>(null, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
