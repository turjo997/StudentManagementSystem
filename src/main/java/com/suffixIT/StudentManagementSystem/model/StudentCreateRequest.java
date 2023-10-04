package com.suffixIT.StudentManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    private Long studentId;
    private Long courseId;
    private String firstName;
    private String lastName;
    private String gender;
    private String studentAddress;
    private String email;


}
