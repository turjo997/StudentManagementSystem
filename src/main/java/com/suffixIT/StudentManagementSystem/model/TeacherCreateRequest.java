package com.suffixIT.StudentManagementSystem.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherCreateRequest {

    private Long teacherId;
    private Long courseId;
    private String firstName;
    private String lastName;
    private String gender;
    private String teacherAddress;
    private String email;


}
