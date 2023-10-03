package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private String gender;
    private String teacherAddress;

    @ManyToMany(cascade=CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<Course> courses;
}
