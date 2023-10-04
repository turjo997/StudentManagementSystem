package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private Integer age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private Set<Course> courses;

}
