package com.suffixIT.StudentManagementSystem.Request;

import com.suffixIT.StudentManagementSystem.entity.Course;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;

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

    @ManyToMany
    @JoinColumn(name = "course_id")
    private ArrayList<Course> courses;


}
