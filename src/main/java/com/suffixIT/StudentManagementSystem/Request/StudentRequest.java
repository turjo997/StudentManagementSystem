package com.suffixIT.StudentManagementSystem.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
}
