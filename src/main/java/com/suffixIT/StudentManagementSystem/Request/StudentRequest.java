package com.suffixIT.StudentManagementSystem.Request;

import com.suffixIT.StudentManagementSystem.entity.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequest {

    @NotEmpty
    @Size(min=2, message="Name should be at least 2 characters")
    private String firstName;

    @NotEmpty
    @Size(min=2, message="Name should be at least 2 characters")
    private String lastName;

    private String gender;

    @NotEmpty
    @Size(min=2, message="Address should be at least 5 characters")
    private String address;


    private List<Integer> courseIds;


}
