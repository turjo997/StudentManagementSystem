package com.suffixIT.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import com.suffixIT.StudentManagementSystem.entity.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponseModel {

    private Long teacherId;
    private String firstName;
    private String lastName;
    private String gender;
    private String teacherAddress;
    private String email;

    @JsonIgnoreProperties({"teachers", "students"})
    private List<CourseEntity> courses;

}
