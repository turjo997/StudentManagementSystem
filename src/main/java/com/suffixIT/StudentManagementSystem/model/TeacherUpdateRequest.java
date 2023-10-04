package com.suffixIT.StudentManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherUpdateRequest {

    private Long teacherId;
    private String firstName;
    private String lastName;
    private String gender;
    private String teacherAddress;
}
