package com.suffixIT.StudentManagementSystem.Request;

import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseRequest {


    @Size(min=2, message = "please insert at least two character!")
    private String title;
    private Double credit;

}
