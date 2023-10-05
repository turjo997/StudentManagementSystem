package com.suffixIT.StudentManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdateRequest {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String studentAddress;
}

