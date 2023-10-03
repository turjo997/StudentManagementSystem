package com.suffixIT.StudentManagementSystem.Request;

import com.suffixIT.StudentManagementSystem.entity.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<Course> courses;
}
