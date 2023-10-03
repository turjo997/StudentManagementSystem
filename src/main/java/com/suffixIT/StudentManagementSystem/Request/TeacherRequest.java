package com.suffixIT.StudentManagementSystem.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TeacherRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
}
