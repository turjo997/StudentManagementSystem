package com.suffixIT.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseModel {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String studentAddress;
    private String email;

    @JsonIgnoreProperties({"teachers", "students"})
    private List<CourseEntity> courses;
}
